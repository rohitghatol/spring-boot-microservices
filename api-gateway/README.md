#Overview

The api-gateway application acts the router and authentication and authorization endpoint. 

The Zuul api gateway solves a very common use case where a UI application wants to proxy calls to one or more back end services. This feature is useful for a user interface to proxy to the backend services it requires, avoiding the need to manage CORS and authentication concerns independently for all the backends.For example in our application `/api/user/**` endpoint is mapped to the `user-webservice`. 

It also knows how to invoke the authorization server in case the user is not authenticated. Once the authentication is complete, it relays the OAuth2 token to the respective services so that they can find the authenticated user and provide services.

##Pre-requisites

### Projects that need to be started before
* [config server](/../../blob/master/config-server/README.md) - For pulling the configuration information
* [webserver-registry](/../../blob/master/webservice-registry/README.md) - For starting the Eureka server since the authorization server also is a micro-service that needs to be registered with Eureka server.    

### Running the application
* Build the application by running the `./gradlew clean build` gradle command at the "task-webservice" project root folder	on the terminal.
* If you want to run the application as jar file, then run `java -jar build/libs/sample-api-gateway-0.0.1.jar` command at the terminal.

## External Configuration
Please refer to [user webservice](/../../blob/master/user-webservice/README.md) for details on how the external configuration works. Note that there is separate configuration file for each Spring application; the application should refer to it's own .yml file for configuration.