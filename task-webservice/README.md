#Overview

This application provides the **task** related functionality and serves as one component. It defines the REST endpoints that are used to provide task functionality.

##Pre-requisites

### Projects that need to be started before
* [config server](/../../config-server/README.md) - For pulling the configuration information
* [webserver-registry](/../../webserver-registry/README.md) - For starting the Eureka server since the authorization server also is a micro-service that needs to be registered with Eureka server.    

### Running the application
* Build the application by running the `./gradlew clean build` gradle command at the "task-webservice" project root folder	on the terminal.
* If you want to run the application as jar file, then run `java -jar build/libs/sample-task-webservice-0.0.1.jar` command at the terminal.

## External Configuration
Please refer to [user webservice](/../../user-webservice/README.md) for details on how the external configuration works. Note that there is separate configuration file for each Spring application; the application should refer to it's own .yml file for configuration.