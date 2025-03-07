/*
 * Copyright 2013 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */
package org.whispersystems.textsecuregcm.push;

import static org.whispersystems.textsecuregcm.metrics.MetricsUtil.name;

import com.eatthepath.pushy.apns.ApnsClient;
import com.eatthepath.pushy.apns.ApnsClientBuilder;
import com.eatthepath.pushy.apns.DeliveryPriority;
import com.eatthepath.pushy.apns.PushType;
import com.eatthepath.pushy.apns.auth.ApnsSigningKey;
import com.eatthepath.pushy.apns.util.SimpleApnsPayloadBuilder;
import com.eatthepath.pushy.apns.util.SimpleApnsPushNotification;
import com.google.common.annotations.VisibleForTesting;
import io.dropwizard.lifecycle.Managed;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import org.whispersystems.textsecuregcm.configuration.ApnConfiguration;

public class APNSender implements Managed, PushNotificationSender {

  private final ExecutorService executor;
  private final String bundleId;
  private final ApnsClient apnsClient;

  @VisibleForTesting
  static final String APN_VOIP_NOTIFICATION_PAYLOAD = new SimpleApnsPayloadBuilder()
      .setSound("default")
      .setLocalizedAlertMessage("APN_Message")
      .build();

  @VisibleForTesting
  static final String APN_NSE_NOTIFICATION_PAYLOAD = new SimpleApnsPayloadBuilder()
      .setMutableContent(true)
      .setLocalizedAlertMessage("APN_Message")
      .build();

  @VisibleForTesting
  static final String APN_BACKGROUND_PAYLOAD = new SimpleApnsPayloadBuilder()
      .setContentAvailable(true)
      .build();

  @VisibleForTesting
  static final Instant MAX_EXPIRATION = Instant.ofEpochMilli(Integer.MAX_VALUE * 1000L);

  private static final String APNS_CA_FILENAME = "apns-certificates.pem";

  private static final Timer SEND_NOTIFICATION_TIMER = Metrics.timer(name(APNSender.class, "sendNotification"));

  public APNSender(ExecutorService executor, ApnConfiguration configuration)
      throws IOException, NoSuchAlgorithmException, InvalidKeyException
  {
    this.executor = executor;
    this.bundleId = configuration.bundleId();
    this.apnsClient = new ApnsClientBuilder().setSigningKey(
            ApnsSigningKey.loadFromInputStream(new ByteArrayInputStream(configuration.signingKey().value().getBytes()),
                configuration.teamId().value(), configuration.keyId().value()))
        .setTrustedServerCertificateChain(getClass().getResourceAsStream(APNS_CA_FILENAME))
        .setApnsServer(configuration.sandbox() ? ApnsClientBuilder.DEVELOPMENT_APNS_HOST : ApnsClientBuilder.PRODUCTION_APNS_HOST)
        .build();
  }

  @VisibleForTesting
  public APNSender(ExecutorService executor, ApnsClient apnsClient, String bundleId) {
    this.executor = executor;
    this.apnsClient = apnsClient;
    this.bundleId = bundleId;
  }

  @Override
  public CompletableFuture<SendPushNotificationResult> sendNotification(final PushNotification notification) {
    final String topic = switch (notification.tokenType()) {
      case APN -> bundleId;
      case APN_VOIP -> bundleId + ".voip";
      default -> throw new IllegalArgumentException("Unsupported token type: " + notification.tokenType());
    };

    final boolean isVoip = notification.tokenType() == PushNotification.TokenType.APN_VOIP;

    final String payload = switch (notification.notificationType()) {
      case NOTIFICATION -> {
        if (isVoip) {
          yield APN_VOIP_NOTIFICATION_PAYLOAD;
        } else {
          yield notification.urgent() ? APN_NSE_NOTIFICATION_PAYLOAD : APN_BACKGROUND_PAYLOAD;
        }
      }

      case ATTEMPT_LOGIN_NOTIFICATION_HIGH_PRIORITY -> new SimpleApnsPayloadBuilder()
          .setMutableContent(true)
          .setLocalizedAlertMessage("APN_Message")
          .addCustomProperty("attemptLoginContext", notification.data())
          .build();

      case CHALLENGE -> new SimpleApnsPayloadBuilder()
          .setContentAvailable(true)
          .addCustomProperty("challenge", notification.data())
          .build();

      case RATE_LIMIT_CHALLENGE -> new SimpleApnsPayloadBuilder()
          .setContentAvailable(true)
          .addCustomProperty("rateLimitChallenge", notification.data())
          .build();
    };

    final PushType pushType;

    if (isVoip) {
      pushType = PushType.VOIP;
    } else {
      pushType = notification.urgent() ? PushType.ALERT : PushType.BACKGROUND;
    }

    final DeliveryPriority deliveryPriority =
        (notification.urgent() || isVoip) ? DeliveryPriority.IMMEDIATE : DeliveryPriority.CONSERVE_POWER;

    final String collapseId =
        (notification.notificationType() == PushNotification.NotificationType.NOTIFICATION && notification.urgent() && !isVoip)
            ? "incoming-message" : null;

    final Instant start = Instant.now();

    return apnsClient.sendNotification(new SimpleApnsPushNotification(notification.deviceToken(),
        topic,
        payload,
        MAX_EXPIRATION,
        deliveryPriority,
        pushType,
        collapseId))
        .whenComplete((response, throwable) -> {
          // Note that we deliberately run this small bit of non-blocking measurement on the "send notification" thread
          // to avoid any measurement noise that could arise from dispatching to another executor and waiting in its
          // queue
          SEND_NOTIFICATION_TIMER.record(Duration.between(start, Instant.now()));
        })
        .thenApplyAsync(response -> {
          final boolean accepted;
          final String rejectionReason;
          final boolean unregistered;

          if (response.isAccepted()) {
            accepted = true;
            rejectionReason = null;
            unregistered = false;
          } else {
            accepted = false;
            rejectionReason = response.getRejectionReason().orElse("unknown");
            unregistered = ("Unregistered".equals(rejectionReason) || "BadDeviceToken".equals(rejectionReason));
          }

          return new SendPushNotificationResult(accepted, rejectionReason, unregistered);
        }, executor);
  }

  @Override
  public void start() {
  }

  @Override
  public void stop() {
    this.apnsClient.close().join();
  }
}
