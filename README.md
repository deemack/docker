# docker
This repo is for docker configuration files used to create docker images.

- Install Docker Desktop if on Windows.
- Clone the repo.
- Navigate to the directory of the Dockerfile for the image you want to build.
- run:
````
docker build -t deemack/<image-name> .

(e.g.) docker build -t deemack/minidlna .
````
- Upload the image to docker hub so that it may be pulled and used in containers.
