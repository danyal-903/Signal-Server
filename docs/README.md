# Signal-Server Docs Table of Contents

`documented-sample-secrets-bundle.yml`

- A copy of the `config-secrets-bundle.yml` that the server needs passed in at runtime, filled out with some comments and dummy values

  - Beware! the comments might be outdated, but regardless just follow `signal-server-configuration.md`

`documented-sample.yml`

- A copy of the `config.yml` that the server also needs at runtime, with comments and dummy values (also possibly with outdated comments)

`sample-credentials`

- A sample of an automatically generated AWS `credentials` file. Originally created to attempt to locally deploy Signal-Server

  - Since, the error this file was attempting to fix was fixed by running in EC2

`sample-secrets.sh`

- A file to set all required environmental variables

`sample-secrets.md`

- A file to keep the certificates generated after running the server

`signal-server-configuration.md`

- Exhuastive documentation on all the dependencies required by `config.yml` and `config-secrets-bundle.yml`

`signal-server-wiki-api-protocol.md`

- The only file remaining in Signal-Server's deprecated (and removed) wiki

  - However, the information here is still completely relavent! So who knows why they removed it