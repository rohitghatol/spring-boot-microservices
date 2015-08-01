#Overview

This application loads and makes the **external configuration** available to rest of the applications. Note that the external configuration can be hosted either on GitHub or on local file system.

The respective applications pick up the configuration based on a `<spring.application.name>.yml` file defined in the configuration by matching the service's `spring.application.name` property defined in the `bootstrap.yml` file.

##Pre-requisites

### Projects that need to be started before
* This is the first application that needs to run since it pulls the configuration information(like what port to run etc) that is needed by rest of the applications to start.

### Running the application
* Build the application by running the `./gradlew clean build` gradle command at the "config-server" project root folder on the terminal.
* If you want to run the application as jar file, then run `java -jar build/libs/sample-config-server-0.0.1.jar` command at the terminal.
