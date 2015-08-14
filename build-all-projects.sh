#!/bin/sh

cd api-gateway; ./gradlew clean build; cd ..
cd auth-server; ./gradlew clean build; cd ..
cd config-server; ./gradlew clean build; cd ..
cd task-webservice; ./gradlew clean build; cd ..
cd user-webservice; ./gradlew clean build; cd ..
cd web-portal; ./gradlew clean build; cd ..
cd webservice-registry; ./gradlew clean build; cd ..
cd comments-webservice; ./gradlew clean build; cd ..
