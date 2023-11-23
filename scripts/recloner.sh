#!/bin/bash
# this script is useful for when the server fails to compile, as it won't sucessfully recompile after a single failed compilation


# cd back to the working directory
cd ..

# get the path of the working directory
folder=$(pwd)

# move outside the working directory to reclone from
cd ..

sudo mv "$folder/personal-config" ./

sudo rm -rf "$folder"

git clone https://github.com/danyal-903/Signal-Server.git

sudo mv Signal-Server "$folder"

sudo rm -rf "$folder"/personal-config

sudo mv personal-config "$folder"

cd "$folder"/scripts