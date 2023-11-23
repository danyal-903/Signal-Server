# Signal-Server Scripts Table of Contents

## Folders

`intact` and `post-surgery`

- contain `WhisperServerService.java` files that either have `zkgroup` dependencies or have them removed respectively

## Files

- All scripts have some things in common, like being called with `bash script.sh` or `cd`ing down a directory at the beginning and `cd scripts` at the end

`docker-compose-first-run.sh`

- A script that downloads Bitnami's official Redis-Cluster `docker-compose.yml`, runs it to generate the correct volumes, and deletes the extra `docker-compose-first-run.yml`

`main-compiler.sh`

- A script that copies the `WhisperServerService.java` from `intact`, then compiles with `./mvnw`

`quickstart.sh`

- A script that tries to do everything without you noticing

- Will try to automatically find the correct `server.jar` to start, then start dependencies and the server using config files from `personal-config` (and stop dependencies after receiving a `^C`)

`recloner.sh`

- A script that conserves the `personal-config` folder, then `rm -rf`'s the whole `Signal-Server` folder and re`git clone`'s this repo

  - Unfortunately this script is necessary, as running `git reset` or similar sometimes won't stop weird maven errors that can be fixed with a fresh reclone

`runtime.sh`

- A script that bundles all dependencies required for Signal-Server into a single folder, and generates the required certificates

`surgery-compiler.sh`

- A script that copies the `WhisperServerService.java` from `post-surgery`, then compiles with `./mvnw`