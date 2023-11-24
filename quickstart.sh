#!/bin/bash

# Find the JAR file matching the name pattern -ChatGPT
jar_file=$(find service/target -name "TextSecureServer*.jar" ! -name "*-tests.jar" | head -n 1)

# Check if a valid JAR file is found -ChatGPT
if [[ -n "$jar_file" && -f "$jar_file" ]]; then
  echo -e "\nStarting Signal-Server using $jar_file\n"
  # Export the environmental variables when starting the server instead of keeping them in .bashrc
  sudo docker-compose up -d
  # Sleep so that the cluster will be reachable by the time Signal-Server attempts to connect
  sleep 4
  # Start the server with the selected JAR file and configuration, and filter the output
  java -jar -Dsecrets.bundle.filename=config/config-secrets-bundle.yml "$jar_file" server config/config.yml | awk '{
      gsub(/WARN /, "\033[33m&\033[0m");
      gsub(/ERROR/, "\033[31m&\033[0m");
      gsub(/INFO/, "\033[32m&\033[0m");
  }
  /Timing: [0-9]+ ms/,/<\/html>/ {next}
  !/^\s*$/ {
     print
  }'
else
  echo -e "\nNo valid Signal-Server JAR file found." # Else echo that the server couldn't be found -ChatGPT
  sudo docker-compose down
  cd scripts
  sleep 2
  exit
fi

# Get the process ID (PID) of the Java process -ChatGPT
JAVA_PID=$!
echo -e "\nProcess running id: $JAVA_PID\n"
# Wait for the Java process to exit -ChatGPT
#while kill -0 $JAVA_PID > /dev/null 2>&1; do
#    sleep 1
#done

#echo -e "\nStopped $jar_file\n"
