#!/bin/bash

# cd back to the working directory
cd ..

# sudo isn't explicitly required here, but sometimes there are problems without sudo
sudo cp scripts/post-surgery/WhisperServerService.java service/src/main/java/org/whispersystems/textsecuregcm

# source is used here as a redundancy to make sure maven runs correctly
source mvnw clean install -DskipTests -Pexclude-spam-filter

cd scripts