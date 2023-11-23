#!/bin/bash

cd ..

jar_file=$(find service/target -name "TextSecureServer*.jar" ! -name "*-tests.jar" | head -n 1)
sudo cp "$jar_file" runtime/Signal-Server.jar
sudo cp -r personal-config runtime/personal-config