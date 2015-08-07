#Overview

This is a Single Page Application(SPA) that has the user facing UI beinng used in the application. It uses the following technologies
* Angular - (base, routes)
* Bootstrap for CSS and layout
* Gradle for build - we could have used grunt/gulp instead

It is perfectly acceptable to have the SPA not start using the Spring boot application. You would only need to change the Zuul route to forward the request to the actual SPA hosted URL.

##Pre-requisites

### Projects that need to be started before
* [config server](/../../blob/master/config-server/README.md) - For pulling the configuration information
* [webserver-registry](/../../blob/master/webservice-registry/README.md) - For starting the Eureka server since the authorization server also is a micro-service that needs to be registered with Eureka server.    

### Running the application
* Build the application by running the `./gradlew clean build` gradle command at the "web-portal" project root folder	on the terminal.
* If you want to run the application as jar file, then run `java -jar build/libs/sample-web-portal-0.0.1.jar` command at the terminal.

## External Configuration
Please refer to [user webservice](/../../blob/master/user-webservice/README.md) for details on how the external configuration works. Note that there is separate configuration file for each Spring application; the application should refer to it's own .yml file for configuration.
