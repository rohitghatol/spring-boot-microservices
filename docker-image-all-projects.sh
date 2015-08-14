#!/bin/sh

cd api-gateway; ./gradlew clean build distDocker; cd ..
cd auth-server; ./gradlew clean build distDocker; cd ..
cd config-server; ./gradlew clean build distDocker; cd ..
cd task-webservice; ./gradlew clean build distDocker; cd ..
cd user-webservice; ./gradlew clean build distDocker; cd ..
cd web-portal; ./gradlew clean build distDocker; cd ..
cd webservice-registry; ./gradlew clean build distDocker; cd ..
cd comments-webservice; ./gradlew clean build distDocker; cd ..