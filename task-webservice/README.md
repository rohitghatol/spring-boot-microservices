#Overview

This application provides the **task** related functionality and serves as one component. It defines the REST endpoints that are used to provide task functionality.

This micro-service also provides an example of to call another OAuth2 protected service from within this service using OAuth2 client configuration. The OAuth2 bearer token that has been passed to the task service is propogated to the "comments" service to get the comments for the given task.

##Pre-requisites

### Projects that need to be started before
* [config server](/../../blob/master/config-server/README.md) - For pulling the configuration information
* [webserver-registry](/../../blob/master/webservice-registry/README.md) - For starting the Eureka server since the authorization server also is a micro-service that needs to be registered with Eureka server.    

### Running the application
* Build the application by running the `./gradlew clean build` gradle command at the "task-webservice" project root folder	on the terminal.
* If you want to run the application as jar file, then run `java -jar build/libs/sample-task-webservice-0.0.1.jar` command at the terminal.

## External Configuration
Please refer to [user webservice](/../../blob/master/user-webservice/README.md) for details on how the external configuration works. Note that there is separate configuration file for each Spring application; the application should refer to it's own .yml file for configuration.