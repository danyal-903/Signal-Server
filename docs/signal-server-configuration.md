# Config Documentation

-   This is documentation for filling out `sample.yml` and `sample-secrets-bundle.yml` inside `/service/config/`

    -   Follow all of [Required Dependencies](#required) - the `Optional` and `Unknown` sections are there to document how much has been configured

-   [Here](documented-sample.yml) is an example of `sample.yml` with extra comments and some sections filled out
-   [Here](documented-sample-secrets-bundle.yml) is an example of `sample-secrets-bundle.yml` with added comments and some sections filled out
-   [Here](sample-secrets.sh) is an example of a file that manages your environmental variables
-   [Here](sample-secrets.md) is a sample file to store any other sensitive Signal-Server keys / data

## Dependencies

### [Required](#dependencies-verbose)

[Apple Push Notifications](#apple-push-notifications-apn)

-   In `sample.yml`
    -   apn
-   In `sample-secrets-bundle.yml`
    -   apn.signingKey

[Braintree](#braintree)

-   In `sample.yml`
    -   braintree

[Redis](#redis-notes)

-   In `Sample.yml`
    -   cacheCluster
    -   clientPresenceCluster
    -   pubsub
    -   pushSchedulerCluster
    -   rateLimitersCluster
    -   messageCache
    -   metricsCluster

[UnidentifiedDelivery](#unidentifieddelivery)

-   In `sample.yml`
    -   unidentifiedDelivery
-   In `sample-secrets-bundle.yml`
    -   unidentifiedDelivery.certificate
    -   unidentifiedDelivery.privateKey

[ZkConfig](#zkconfig)

-   In `sample.yml`
    -   zkConfig
    -   genericZkConfig
-   In `sample-secrets-bundle.yml`
    -   zkConfig.serverSecret
    -   genericZkConfig.serverSecret

[hCaptcha](#hcaptcha)

-   In `sample.yml`
    -   hCaptcha
-   In `sample-secrets-bundle.yml`
    -   hCaptcha.apiKey

[AWS](#aws)

-   In `sample.yml`
    -   dynamoDbClientConfiguration
    -   dynamoDbTables
    -   awsAttachments
    -   cdn
    -   appConfig
-   In `sample-secrets-bundel.yml`
    -   awsAttachments.accessKey
    -   awsAttachments.accessSecret
    -   cdn.accessKey
    -   cdn.accessSecret

[Google Cloud](#google-cloud)

-   In `sample.yml`
    -   adminEventLoggingConfiguration
    -   gcpAttachments
    -   fcm
    -   recaptcha
-   In `sample-secrets-bundel.yml`
    -   gcpAttachments.rsaSigningKey
    -   fcm.credentials

[External Service Configuration](#external-service-configuration)

-   In `sample.yml`
    -   svr2
    -   storageService
    -   backupService
    -   registrationService
-   In `sample-secrets-bundle.yml`
    -   svr2.userAuthenticationTokenSharedSecret
    -   svr2.userIdTokenSharedSecret
    -   storageService.userAuthenticationTokenSharedSecret
    -   backupService.userAuthenticationTokenSharedSecret

[Configuring for `quickstart.sh`](#configuring-for-quickstartsh)

### Optional

Leave untouched:

-   In `sample.yml`
    -   Datadog
    -   Stripe
    -   paymentsService
    -   subscription
    -   oneTimeDoncations
    -   badges
-   In `sample-secrets-bundle.yml`
    -   datadog.apiKey
    -   stripe.apiKey
    -   stripe.idempotencyKeyGenerator
    -   braintree.privateKey
    -   paymentsService.userAuthenticationTokenSharedSecret
    -   paymentsService.fixerApiKey
    -   paymentsService.coinMarketCapApiKey

### Unknown (will get sorted into the above)

In `sample.yml`

-   DirectoryV2
-   remoteConfig
-   artService

In `sample-secrets-bundle.yml`

-   directoryV2.client.userAuthenticationTokenSharedSecret
-   directoryV2.client.userIdTokenSharedSecret
-   remoteConfig.authorizedTokens
-   artService.userAuthenticationTokenSharedSecret
-   artService.userAuthenticationTokenUserIdSecret
-   currentReportingKey.secret
-   currentReportingKey.salt

# Dependencies (verbose)

## External Service Configuration

-   `svr2` > `svrCaCertificates`, `storageService` > `storageCaCertificates`, `backupService` > `backupCaCertificates`, and `registrationService` > `registrationCaCertificate` all only look for a valid key of any kind

-   Using an example copy-pasted key works fine for all of these sections:

```
-----BEGIN CERTIFICATE-----
MIICUTCCAfugAwIBAgIBADANBgkqhkiG9w0BAQQFADBXMQswCQYDVQQGEwJDTjEL
MAkGA1UECBMCUE4xCzAJBgNVBAcTAkNOMQswCQYDVQQKEwJPTjELMAkGA1UECxMC
VU4xFDASBgNVBAMTC0hlcm9uZyBZYW5nMB4XDTA1MDcxNTIxMTk0N1oXDTA1MDgx
NDIxMTk0N1owVzELMAkGA1UEBhMCQ04xCzAJBgNVBAgTAlBOMQswCQYDVQQHEwJD
TjELMAkGA1UEChMCT04xCzAJBgNVBAsTAlVOMRQwEgYDVQQDEwtIZXJvbmcgWWFu
ZzBcMA0GCSqGSIb3DQEBAQUAA0sAMEgCQQCp5hnG7ogBhtlynpOS21cBewKE/B7j
V14qeyslnr26xZUsSVko36ZnhiaO/zbMOoRcKK9vEcgMtcLFuQTWDl3RAgMBAAGj
gbEwga4wHQYDVR0OBBYEFFXI70krXeQDxZgbaCQoR4jUDncEMH8GA1UdIwR4MHaA
FFXI70krXeQDxZgbaCQoR4jUDncEoVukWTBXMQswCQYDVQQGEwJDTjELMAkGA1UE
CBMCUE4xCzAJBgNVBAcTAkNOMQswCQYDVQQKEwJPTjELMAkGA1UECxMCVU4xFDAS
BgNVBAMTC0hlcm9uZyBZYW5nggEAMAwGA1UdEwQFMAMBAf8wDQYJKoZIhvcNAQEE
BQADQQA/ugzBrjjK9jcWnDVfGHlk3icNRq0oV7Ri32z/+HQX67aRfgZu7KWdI+Ju
Wm7DCfrPNGVwFWUQOmsPue9rZBgO
-----END CERTIFICATE-----
```

-   All of these sections are ssl certificates for other required self-hosted services - namely, [registration-service](https://github.com/signalapp/registration-service), [storage-service](https://github.com/signalapp/storage-service), and [SecureValueRecoveryV2](https://github.com/signalapp/SecureValueRecovery2)

-   Out of all of these, only `registration-service` has been sucessfully deployed. Here is what you need to do for `registration-service` (or just use a dummy value until you get all the other pieces):

    -   The `registrationService` `host` and `port` specify where your registration-service instance is running, and the Signal-Server will attempt to connect to it over `https`

    -   The `registrationCaCertificate` is a root certificate taken from [letsencrypt.org](letsencrypt.org) - you can also get the `.pem` [here](https://letsencrypt.org/certificates/) - select the Active, Self-signed pem link

```
registrationService:
  host: chat.your.domain
  port: 442

. . .

  identityTokenAudience: https://chat.your.domain
  registrationCaCertificate: |
    -----BEGIN CERTIFICATE-----
    MIIFFjCCAv6gAwIBAgIRAJErCErPDBinU/bWLiWnX1owDQYJKoZIhvcNAQELBQAw
    TzELMAkGA1UEBhMCVVMxKTAnBgNVBAoTIEludGVybmV0IFNlY3VyaXR5IFJlc2Vh
    cmNoIEdyb3VwMRUwEwYDVQQDEwxJU1JHIFJvb3QgWDEwHhcNMjAwOTA0MDAwMDAw
    WhcNMjUwOTE1MTYwMDAwWjAyMQswCQYDVQQGEwJVUzEWMBQGA1UEChMNTGV0J3Mg
    RW5jcnlwdDELMAkGA1UEAxMCUjMwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEK
    AoIBAQC7AhUozPaglNMPEuyNVZLD+ILxmaZ6QoinXSaqtSu5xUyxr45r+XXIo9cP
    R5QUVTVXjJ6oojkZ9YI8QqlObvU7wy7bjcCwXPNZOOftz2nwWgsbvsCUJCWH+jdx
    sxPnHKzhm+/b5DtFUkWWqcFTzjTIUu61ru2P3mBw4qVUq7ZtDpelQDRrK9O8Zutm
    NHz6a4uPVymZ+DAXXbpyb/uBxa3Shlg9F8fnCbvxK/eG3MHacV3URuPMrSXBiLxg
    Z3Vms/EY96Jc5lP/Ooi2R6X/ExjqmAl3P51T+c8B5fWmcBcUr2Ok/5mzk53cU6cG
    /kiFHaFpriV1uxPMUgP17VGhi9sVAgMBAAGjggEIMIIBBDAOBgNVHQ8BAf8EBAMC
    AYYwHQYDVR0lBBYwFAYIKwYBBQUHAwIGCCsGAQUFBwMBMBIGA1UdEwEB/wQIMAYB
    Af8CAQAwHQYDVR0OBBYEFBQusxe3WFbLrlAJQOYfr52LFMLGMB8GA1UdIwQYMBaA
    FHm0WeZ7tuXkAXOACIjIGlj26ZtuMDIGCCsGAQUFBwEBBCYwJDAiBggrBgEFBQcw
    AoYWaHR0cDovL3gxLmkubGVuY3Iub3JnLzAnBgNVHR8EIDAeMBygGqAYhhZodHRw
    Oi8veDEuYy5sZW5jci5vcmcvMCIGA1UdIAQbMBkwCAYGZ4EMAQIBMA0GCysGAQQB
    gt8TAQEBMA0GCSqGSIb3DQEBCwUAA4ICAQCFyk5HPqP3hUSFvNVneLKYY611TR6W
    PTNlclQtgaDqw+34IL9fzLdwALduO/ZelN7kIJ+m74uyA+eitRY8kc607TkC53wl
    ikfmZW4/RvTZ8M6UK+5UzhK8jCdLuMGYL6KvzXGRSgi3yLgjewQtCPkIVz6D2QQz
    CkcheAmCJ8MqyJu5zlzyZMjAvnnAT45tRAxekrsu94sQ4egdRCnbWSDtY7kh+BIm
    lJNXoB1lBMEKIq4QDUOXoRgffuDghje1WrG9ML+Hbisq/yFOGwXD9RiX8F6sw6W4
    avAuvDszue5L3sz85K+EC4Y/wFVDNvZo4TYXao6Z0f+lQKc0t8DQYzk1OXVu8rp2
    yJMC6alLbBfODALZvYH7n7do1AZls4I9d1P4jnkDrQoxB3UqQ9hVl3LEKQ73xF1O
    yK5GhDDX8oVfGKF5u+decIsH4YaTw7mP3GFxJSqv3+0lUFJoi5Lc5da149p90Ids
    hCExroL1+7mryIkXPeFM5TgO9r0rvZaBFOvV2z0gp35Z0+L4WPlbuEjN/lxPFin+
    HlUjr8gRsI3qfJOQFy/9rKIJR0Y/8Omwt/8oTWgy1mdeHmmjk7j1nYsvC9JSQ6Zv
    MldlTTKB3zhThV1+XWYp6rjd5JW1zbVWEkLNxE7GJThEUG3szgBVGP7pSWTUTsqX
    nLRbwHOoq7hHwg==
    -----END CERTIFICATE-----
```

-   `registrationService` also requires extra configuration with AWS and Google Cloud:

### Creating a Cognito User Pool and Integrating with Google Cloud

-   In AWS, go to `Cognito`

-   Select `Create user pool`

-   Choose `Federated identity providers` and select `Google` from the options that appear

    -   I believe that the intended deployment is to select `Phone number` / `SMS`, since this is how verification codes will be sent while registering a phone number

    -   So long as you are using the `dev` environment for `registration-service`, it doesn't matter how you set this up (just that you do in the first place), since the code is consistent every session

    -   In Step 5, make sure to select `Confidential client` under `Initial app client`

-   Integrating this Cognito User Pool with Google Cloud (heavily following [this guide](https://cloud.google.com/iam/docs/workload-identity-federation-with-other-clouds))

    -   Go to hamburger menu > `IAM and Admin` > `Workload Identity Federation` and hit `CREATE POOL`

    -   Select `AWS` as the pool's provider

    -   For `Provider ID`: go to AWS > `Cognito` > the created User Pool > `App integration` > scroll to the bottom to the `App clients and analytics` section and copy the `Client ID`

    -   For `AWS account ID`: click on your name in the top right, and the dropdown will have your account ID then create the pool

    -   Create a new service account (hamburger menu > `IAM and Admin` > `Service Accounts`) with the roles: `Owner`, `IAM Workload Identity Pool Admin` and `Service Account OpenID Connect Identity Token Creator`

    -   Back in `Workload Identity Federation` > the created pool, hit `GRANT ACCESS` in the top middle-ish, and select the new service account that was just created

    -   On the right, select `CONNECTED SERVICE ACCOUNTS` and hit `Download` under `Client Library Config`

        -   Open the `.json` and make sure that it looks something like this:

```
{
  "type": "external_account",
  "audience": "//iam.googleapis.com/projects/<example_url>",
  "subject_token_type": "gibberish:gibberish",
  "service_account_impersonation_url": "https://iamcredentials.googleapis.com/<example_url>",
  "token_url": "https://sts.googleapis.com/<example_url>",
  "credential_source": {
    "environment_id": "aws1",
    "region_url": "http://169.254.169.254/<example_url>",
    "url": "http://169.254.169.254/<example_url>",
    "regional_cred_verification_url": "https://sts.{region}.amazonaws.com<example-url>"
  }
}
```

-   If everything looks right, paste the `.json` into `sample.yml` > `registrationService` > `credentialConfigurationJson` and `secondaryCredentialConfigurationJson`

### Creating an Identity Pool and integrating with Google Cloud

-   In Google Cloud, go to hamburger menu > `APIs & Services` > `Credentials` > `CREATE CREDENTIALS` > `OAuth Client ID`

    -   Choose `Android` as the `Application type`

    -   Enter in the new package name from Firebase and the SHA1 from your `debug.store` (probably located in `.android`)

        -   If you can't find the `debug.keystore`, make sure you have Android Studio installed and try finding it with `locate debug.keystore`

    -   Generate the `SHA1` with the Google provided command:

```
cd ~/.android
keytool -keystore debug.keystore -list -v
```

-   Now link this OAuth2.0 Client ID to an AWS Identity Pool:

-   Go to `Cognito` > `Identity pools` > `Create identity pool`

-   Choose `Authenticated access` > `Google`

-   Reuse the `IAM role` from your `User pool`

-   Enter the `Client ID` from your Google Cloud OAuth2.0 Client ID

    -   If you want to set this up later, you can find it at `Cognito` > `Identity pools` > your pool > `User access` > `Identity providers` > `Add identity provider`

-   All of this configuration gets called by the one external account added to the `credentialConfigurationJson`, though none of it will actually function (but it will throw errors without)

### Roadblocks

-   `registration-service` wants to send sms verification codes, which both requires setting the service up and getting a phone number from AWS

    -   Getting a phone number is much more heavily regulated compared to other AWS services, and you almost definitely won't be able to get one for a personal deployment

    -   You might be able to configure `Twilio` in `registration-service`, but for now the `dev` environment works fine

## Configuring for `quickstart.sh`

-   `quickstart.sh` is located in `Signal-Server/scripts` and is called with `bash quickstart.sh`

-   It looks for a `config.yml`, `config-secrets-bundle.yml`, and `secrets.env` located in `Signal-Server/personal-config`

    -   The contents of this folder are already `.gitignore`'d and gets perserved between reclones when using [`bash recloner.sh`](../scripts/recloner.sh)

-   The script should work out of the box - it should start all dependencies, find the correct .jar regardless of version, and ask to stop the redis-cluster after the server stops

    -   In case it doesn't function, a barebones version is commented out at the bottom - either run each line or create a new bash script in `Signal-Server`

    -   Note: `quickstart.sh` was written in bash, and most won't necessarily work somehwere else (I know it doesn't work in zsh, but haven't tried fish etc)

## Dockerized Signal-Server Documentation

Update all redis sections in `config.yml` to:

```
  configurationUri: redis://redis-node-5:6379
```

Which will tell Signal-Server to look for the redis-cluster in a docker-network

The `personal-config` folder is functionally the same as in the Main branch, and gets mounted at startup

-   `personal-config` needs a completed `config.yml`, `config-secrets-bundle`, and `secrets.env` in order for the server to start properly (assuming correct configuration)

## Apple Push Notifications (APN)

-   For actual APN implementation, [this guide](https://developer.apple.com/documentation/usernotifications/setting_up_a_remote_notification_server/establishing_a_certificate-based_connection_to_apns) might help

-   I am using dummy / test values until it becomes a problem (already in `documented-sample-secrets-bundle.yml`)

-   In `sample-secrets-bundle.yml`, replace the `apn.signingKey` with:

-   In `sample-secrets-bundle.yml`

    -   hCaptcha.apiKey

-   This key is a dummy key but should stop any apn key-related errors

-   It was generated with:

```
keytool -genkeypair -alias mykey -keyalg EC -keysize 256 -sigalg SHA256withECDSA -keystore keystore.p12 -storetype PKCS12

openssl pkcs12 -in keystore.p12 -nodes -nocerts -out ec_private_key.pem
```

## hCaptcha

-   hCaptcha is required for registering a phone number on Signal-Android, and it checks the sitekey on the self-hosted captcha website with the secret in `sample-secrets-bundle.yml`

-   Go to [hcaptcha.com](https://hcaptcha.com), create an account (I used my gmail), and copy the `Sitekey` and `Secret` that pop up

    -   If you need to get to these again, you can acess the sitekey from the `Sites` tab and the `Secret` from your account picture > `Settings`

Then paste the `Secret` key into `sample-secrets-bundle.yml`:

```
hCaptcha.apiKey: your-secret
```

And hold onto the sitekey for when you set up AWS AppConfig

## AWS

### AWS IAM Configuration

-   Create an account with AWS

-   Go to IAM and create a user with full access (this is definitely not entirely safe but hey, it gets the job done)
-   Copy the access key and access secret, and paste them into `awsAttachments.accessKey` and `awsAttachments.accessSecret`, and `cdn.accessKey` and `cdn.accessSecret` in [sample-secrets-bundle](/service/config/sample-secrets-bundle.yml)

-   If you give the IAM user full access, you can reuse the same access key and secret for both buckets

### AWS appConfig

-   Search for `AWS AppConfig` and hit `Create Application`

-   Under `Configuration Profiles and Feature Flags`, hit `CREATE` and choose `Feature Flag`. Enter a name and hit `Freeform configuration profile`

-   Select `YAML` as the type of `Freeform Configuration`

    -   Enter the following lines, and replace the `sitekey` with the key generated from the `hcaptcha` section:

```
captcha:
  scoreFloor: 1.0
  allowHCaptcha: true
  hCaptchaSiteKeys:
    challenge:
      - sitekey
    registration:
      - sitekey
```

-   The `scoreFloor` section is supposed to be the "score" needed to avoid a captcha during registration, though it doesn't appear to do anything besides cause an error if missing

-   In the `Environments` tab, select `Create Environment`, enter a name, and ceate the environment

-   Do something with versions

-   Hit `Create Application` and choose immediately for the timeframe

-   Enter the names of the application, environment, and configuration into their sections under `appConfig` in `sample.yml`:

```
appConfig:
  application:
  environment:
  configuration:
```

### AWS Buckets Configuration

-   Go to S3 and create the two buckets below (I don't think you need to change any other settings)

    -   aws-attachments

    -   cdn

### AWS DynamoDb Configuration

-   DynamoDB handles storing data about accounts, phone numbers, and messages

-   Go to `DynamoDB` > `Tables` > `Create table` > enter the table details from below > Hit `Customize settings` > Under `Read/write capacity settings` > Select `On-demand`

    -   Selecting `On-demand` will avoid making `Cloudwatch` alarms that cost more than just paying as you go

-   Sign into AWS, look up DynamoDb, and make tables of all of the following:

-   If you change these names, also change them in [sample.yml](/service/config/sample.yml)

    -   Accounts
        -   Partition key: `U` - Binary
    -   Accounts_PhoneNumbers
        -   Partition key: `PNI` - Binary
    -   Accounts_PhoneNumberIdentifiers
        -   Partition key: `P` - String
    -   Accounts_Usernames
        -   Partition key: `N` - Binary
    -   DeletedAccounts
        -   Partition key: `P` - String
    -   DeletedAccountsLock
        -   Partition key: `P` - String
    -   IssuedReceipts
        -   Partition key: `A` - String
    -   Keys
        -   Partition key: `U` - Binary
        -   Sort Key: `DK` - Binary
    -   PQ_Keys
        -   Partition key: `U` - Binary
        -   Sort Key: `DK` - Binary
    -   PQ_Last_Resort_Keys
        -   Partition key: `U` - Binary
        -   Sort Key: `DK` - Binary
    -   Messages
        -   Partition key: `H` - Binary
        -   Sort key: `S` - Binary
    -   PendingAccounts
        -   Partition key: `P` - String
    -   PendingDevices
        -   Partition key: `P` - String
    -   PhoneNumberIdentifiers
        -   Partition key: `P` - String
    -   Profiles
        -   Partition key: `U` - Binary
        -   Sot key: `V` - String
    -   PushChallenge
        -   Partition key: `U` - Binary
    -   RedeemedReceipts
        -   Partition key: `S` - Binary
    -   RegistrationRecovery
        -   Partition key: `P` - String
    -   RemoteConfig
        -   Partition key: `N` - String
    -   ReportMessage
        -   Partition key: `H` - Binary
    -   Subscriptions
        -   Partition key: `U` - Binary
    -   VerificationSessions
        -   Partition key: `K` - String

### AWS CloudWatch

If you make your `DynamoDB` tables with the `Provisioned with auto scaling` setting instead of `On-demand`, eight `Cloudwatch` alarms will be created to handle scaling the read/write of the table

-   Note that deleting these alarms will remove the auto-scaling functionality, but also remove the massive charges from having hundreds of alarms

To minimize charges:

-   Go to `CloudWatch` > `Alarms` dropdown > `All alarms`

-   Select all the alarms (checkbox on the top selects them all) and hit `Actions` > `Delete`

-   Make sure to check out `Billing` and `CloudWatch` from time to time to make sure that extra charges don't crop up

### AWS Environmental Variables

Signal-Server looks at your environmental variables for some AWS credentials

-   You can either set these in your `~/.bashrc` or by renaming and filling out [sample-secrets.sh](sample-secrets.sh)

Going from top to bottom:

`AWS_REGION`

Most self-explanitory: whatever region you use for AWS is what you set this to (i.e. `us-west-1`)

`AWS_ACCESS_KEY_ID` and `AWS_SECRET_ACCESS_KEY`

The same access key and secret from [AWS IAM Configuration](#aws-iam-configuration):

-   Go to `IAM` > `Users` > your user > `Security Credentials` > `Create access key` and copy/paste the ID and key into the respective environmental variable

### AWS EC2

-   All the EC2 documentation is based heavily off of [this](https://docs.aws.amazon.com/IAM/latest/UserGuide/id_roles_use_switch-role-ec2.html#roles-usingrole-ec2instance-permissions) and [this](https://docs.aws.amazon.com/IAM/latest/UserGuide/troubleshoot_iam-ec2.html#troubleshoot_iam-ec2_no-keys) from the AWS documentation. If you have issues, the answer might be in one of those as well

**Creating an EC2 Instance**

Go to `AWS` > `EC2` > `Launch instance` in a panel in the middle of the page

-   For ease of deployment, choose whatever distro is the most comfortable - Ubuntu / Debian has already been thoroughly documented here

-   For `Instance type`, leave it as `t2.micro`, which is free to leave running 24/7

    -   The `t2.micro` might not be beefy enough to run Signal-Server, registration-service, and nginx all at the same time. If you are running into crashes then try upgrading to a `t2.small` or larger (you can do this after initial creation, so no sweat)

        -   If you do want to change: go to `EC2` > your EC2 instance: first, `Instance` > `Stop instance` then `Actions` > `Instance settings` > `Change instance type` and select the desired instance

-   Generate a keypair and save the `.pem` for ssh'ing into the instance

-   You can increase your default storage if you want, up to 30gb is free

**Creating an Elastic IP for the EC2 Instance**

In the left-hand set of dropdowns, select `Elastic IPs` under the `Network & Security` dropdown

Hit `Allocate Elastic IP address`, then hit `Allocate` (the default settings are fine)

-   Now there will either be a green banner to allocate the Elastic IP, or you can select `Actions` > `Associate Elastic IP address`

-   Then, select the newly created EC2 instance from the dropdown and hit `Associate`

Note: The elastic ip is free so long as it is associated with a running ip, but stopping a `t2.small` and paying for the elastic ip is cheaper than leaving it running 24/7

**Interacting with the EC2 Instance**

`ssh` into your EC2 instance:

```
ssh -i "path/to/key.pem" admin@elastic-ip
```

When you need to copy in your configured `personal-config`:

```
scp -i "path/to/key.pem" -r personal-config admin@elastic-ip:/home/admin/Signal-Server
```

Or if you will be actively interacting with these files, you can instead use `sshfs` to mount the EC2's `Signal-Server/personal-config` to the local machine:

```
sshfs -o IdentityFile="$HOME/full/path/to/key.pem" admin@elastic-ip:/home/admin/Work/Signal-Server/personal-config ./personal-config
```

-   Make sure that the local `personal-config` folder already exists

**Installing Signal-Server**

Installing is the same as ever. Install your dependencies (`git`, `java`, `docker`, and `docker-compose`). Run these for the very lazy (assuming you are using Debian-based because I don't like Amazon Linux):

```
sudo apt update && sudo apt upgrade -y && sudo apt install -y git openjdk-19-jre-headless docker docker-compose

git clone https://github.com/danyal-903/Signal-Server.git && cd Signal-Server/scripts && bash surgery-compiler.sh && bash docker-compose-first-run.sh
```

`scp` in your `personal-config` folder, then run with `bash quickstart.sh`

-   Make sure your redis configuration is set to `127.0.0.1:7000` and not the docker branch's `redis-node-5:6379`

If when running the server you get errors about needing to set environmental variables that you already set in `sercrets.env`, you may need to change `secrets.env` to `secrets.sh`, and add `export`:

```
export AWS_REGION=asdf
etc
```

Then edit your `scripts/quickstart.sh` to call the `.sh` instead of the `.env`

**Configuring the EC2 Instance**

The EC2 instance wants to assume an IAM role to access all the required AWS functions

Head over to `IAM` > `Roles` > `Create role` > choose `AWS service`, and select `EC2` as the `Use case`

Add the `AdministratorAccess` policy, then hit `Create policy` > `JSON` and paste this in:

```
{
	"Version": "2012-10-17",
	"Statement": [
		{
			"Sid": "Statement1",
			"Effect": "Allow",
			"Action": [
				"*"
			],
			"Resource": [
				"*"
			]
		}
	]
}
```

-   Note: this is way too open, but I am leaving the policy this permissive until I know what the server needs and doesn't need to access

Finish creating the role, then go to `Roles` > `your-new-ec2-role` > `Trust relationships` tab and take a peek at the `Trusted entities`. It should look like this, but change it if it doesn't:

```
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Principal": {
                "Service": "ec2.amazonaws.com"
            },
            "Action": "sts:AssumeRole"
        }
    ]
}
```

Head back to `EC2` > `Instances` > your instance > `Actions` dropdown > `Security` > `Modify IAM role` > select the newly created IAM role

Now you need to open port 8080, where the server hosts by default. Go to `EC2` > `Instances` > your instance > `Security` tab > the linked security group (probably ends with `launch-wizard` or similar) > `Edit inbound rules`

-   Add a rule, select `Custom TCP` from the dropdown, and enter `8080` in the port range column and `Andywhere-IPv4` in the Source column

You're done! You can test it to make sure everything is configured correctly a couple of ways:

-   You can probe the instance to verify that it is properly assuming your role:

-   SSH into your instance and enter this: `curl http://169.254.169.254/latest/meta-data/iam`

-   Which should return (similar to `ls`ing):

```
info
security-credentials
```

-   Go into the `security-credentials` 'folder' and `curl` what role is assigned to the instance. Then `curl` that role to make sure it is assumed properly

```
curl http://169.254.169.254/latest/meta-data/iam/security-credentials
curl http://169.254.169.254/latest/meta-data/iam/security-credentials/name-of-role
```

-   `curl`ing the role will return this if it is working properly:

```
{
  "Code" : "Success",
  "LastUpdated" : "2023-08-03T20:43:27Z",
  "Type" : "AWS-HMAC",
  "AccessKeyId" : "key",
  "SecretAccessKey" : "key-secret",
  "Token" : "a-very-long-token",
  "Expiration" : "2023-08-04T02:51:32Z"
}
```

-   Run the server and probe it from your host machine:

```
curl http://elastic-ip:8080/v1/accounts/whoami
```

-   Which should return `Credentials are required to access this resource.` if everything went according to plan. If you enter this in a web browser, you will be greeted with an http login prompt

## Braintree

-   Set the `envrionment` for `braintree` in `sample.yml` to `sandbox`

-   Done by default

## Google Cloud

### Initial Configuration

1. Create a Google Cloud accont and enter info / payment

2. Make a project

    2.1. The option should be in the top left, and select `NEW PROJECT`

3. Make a bucket

    3.1. Select `Buckets`, located under `Cloud Storage` inside the left-hand hamburger menu

    3.2. Create a bucket - exact configuration doesn't matter

    3.3. On the `CONFIGURATION` tab inside the bucket, copy the `Cloud Console URL` and paste it into the `domain` section of `gcpAttachments` in `sample.yml`

4. Make a service account and a key

    4.1. Select `Service Accounts`, located under `IAM & Admin` inside hamburger menu

    4.2. In the top middle, select `CREATE SERVICE ACCOUNT`

    4.3. Make sure to add the roles `Owner` and `Cloud KMS CryptoKey Encrypter/Decrypter`

    4.4. Copy the email address of the service account and paste it into the `email` section of `gcpAttachments` in `sample.yml`

    4.5. Select the service account and select the `KEYS` tab

    4.6. Hit `ADD KEY`, then `Create new key` and select `JSON` as the format for the downloaded key

    4.7. Copy and paste the all the contents of the `JSON` into `credentials` under `adminEventLoggingConfiguration` (replacing the existing `key` and curly brackets) in `sample.yml`

5. Make a secret

    5.1. Open `Secret Manager` inside `Security` from the hamburger menu

    5.2. Select `CREATE SECRET` and enter a secret value matching the format from `gcpAttachments.rsaSigningKey` inside `sample-secrets-bundle.yml`

    5.3. Specifically, keep the line breaks and the `-----BEGIN/END PRIVATE KEY-----` (and make sure what is in the Google Cloud Secret and the `rsaSigningKey` match)

### Firebase

-   Go to [Firebase](firebase.google.com) and create a new project

-   When prompted to link this project to an existing one, either create a new one or link it to your main project (it's not recommended by Google but, ah well) (also, this might happen after it is done cooking)

-   Once it is done cooking, hit the Settings cog in the top left and select `Project Settings` > `Service accounts`

-   Select `Java` under `Admin SDK configuration snippet`, then hit `Generate new private key`

-   Paste the `.json` into `fcm.credential` inside `sample-secrets-bundle.yml`

### ReCAPTCHA

-   Go to the hamburger menu > `Security` > `ReCAPTCHA Enterprise`

-   Create `CREATE KEY` and select `Website` as the platform type, and enter a website - `localhost` or a real website to use

-   Create a new service account with the Roles `Owner` and `ReCAPTCHA Enterprise Agent`

-   Select that service account and go to the `KEYS` tab and generate a new `.json`

-   Paste the contents of the `.json` into `sample.yml` > `recaptcha` > `credentialConfigurationJson` in this format:

```
recaptcha:
  projectPath: projects/example
  credentialConfigurationJson: |
    {
      service account .json contents, seperated by commas at the end of each line (except the last line)
    } # remove this comment that was here
  secondaryCredentialConfigurationJson: "{a}" # make sure there is no space between the "}" and this line (also remove this comment)
```

-   Update the `projectPath` to `projects/your-project-name`

    -   Use the full ID of the project, not just the display name - shown when clicking on the project selector in the top left

## Redis Notes

-   The current `docker-compose.yml` and `documented-sample.yml` work together out of the box

    -   The redis-cluster is a lightly modifed version of [this](https://github.com/bitnami/containers/blob/main/bitnami/redis-cluster/docker-compose.yml) docker-compose

        -   I removed the password requirements and added ports for the cluster to be reachable from

-   For some reason, you need to start Bitnami's dockerized redis-cluster completely vanilla first before starting the modified `docker-compose.yml`

    -   Instructions on doing that is back in the [README.md](../README.md#docker-first-run)

## UnidentifiedDelivery

-   Signal-Server wants a certificate in `sample-secrets-bundle.yml` that the server generates:

```
java -jar -Dsecrets.bundle.filename=service/config/sample-secrets-bundle.yml service/target/TextSecureServer-0.0.0-SNAPSHOT.jar certificate -ca
```

-   Which will output something like this:

```
Public key : aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
Private key: aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
```

-   Then take the private key and generate a certificate:

```
java -jar -Dsecrets.bundle.filename=service/config/sample-secrets-bundle.yml service/target/TextSecureServer-0.0.0-SNAPSHOT.jar certificate --key <Private key> --id <any string of letters or numbers>
```

-   Which will output something like this:

```
Certificate: aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
Private key: aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
```

-   I recommend keeping all the outputs somewhere - for example, [sample-secrets.md](sample-secrets.md)

-   Then fill out `unidentifiedDelivery` in `sample.yml`:

```
unidentifiedDelivery:
  certificate: secret://unidentifiedDelivery.certificate
  privateKey: secret://unidentifiedDelivery.privateKey
  expiresDays: 7 # Probably change this to a much larger number so you don't have to worry about it expiring
```

-   And in `sample-secrets-bundle.yml`:

```
unidentifiedDelivery.certificate: aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
unidentifiedDelivery.privateKey: aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
```

## ZkConfig

-   NOTE: you probably (untested) don't have to do this in `post-surgery`

    -   Try leaving the dummy value in and see if you run into problems (if not open an Issue)

-   Generate a `public` and `private` value:

```
java -jar -Dsecrets.bundle.filename=service/config/sample-secrets-bundle.yml service/target/TextSecureServer-0.0.0-SNAPSHOT.jar zkparams
```

-   Which will output this:

```
Public: a really long string
Private: an even longer string
```

-   I (again) recommend putting these values into [`secrets.md`](sample-secrets.md)

-   Put the output into `zkConfig` in `sample.yml`

```
zkConfig:
  serverPublic: the Public: string
  serverSecret: secret://zkConfig.serverSecret
```

-   In `sample-secrets-bundle.yml`

```
zkConfig.serverSecret: the even longer Private: string

genericZkConfig.serverSecret: I'm not sure what goes here
```
