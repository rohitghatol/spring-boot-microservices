#Overview

This application provides the **Eureka Server** that provides service discivery and enables all Eureka clients to discover each other.

When a client registers with Eureka, it provides meta-data about itself such as host and port, health indicator URL, home page etc. Eureka receives heartbeat messages from each instance belonging to a service. If the heartbeat fails over a configurable timetable, the instance is normally removed from the registry.

##Pre-requisites

### Projects that need to be started before
* [config server](/../../blob/master/config-server/README.md) - For pulling the configuration information

### Running the application
* Build the application by running the `./gradlew clean build` gradle command at the "webservice-registry" project root folder	on the terminal.
* If you want to run the application as jar file, then run `java -jar build/libs/sample-webservice-registry-0.0.1.jar` command at the terminal.

## External Configuration
Please refer to [user webservice](/../../blob/master/user-webservice/README.md) for details on how the external configuration works. Note that there is separate configuration file for each Spring application; the application should refer to it's own .yml file for configuration.