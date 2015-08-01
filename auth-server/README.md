# Auth-Server

## Prerequistes

### Setting Authentication Schema Information
  * The mysql database information is available on the config server GitHub configuration.
  * Please look up the "application.yml" file under the "config-server" project for the location of GitHub repository where the database information has to be updated.
  * At the GitHub repository, update the "auth-server.yml" file section below for the mysql database where the authentication schema would be stored.
     ```
     spring:
       datasource:
         url: jdbc:mysql://192.168.59.103:3306/auth
         username: root
         password: password
         driver-class: com.mysql.jdbc.Driver
     ```    
  * At a minumum, you would need to change the JDBC URL to point to where your mysql server is running.  
  
### Running Mysql  
  * Hosted mysql
    * If you have hosted mysql, please create the `auth` database and have `username=root`, `password=password`.
    * You can obviously have different values for user/password/database; just ensure that the "auth-server.yml" file in the configuration Github repository has the correct values.
 * Docker container
   * If you have docker you can simply run the following command on the docker prompt. Note that we are specifically naming the container as auth-db (using --name auth-db) instead of docker provided random names so that we can use the predefined name in subsequent commands.
        ```
        docker run -d -e  MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=auth --name auth-db -p 3306:3306 mysql
        ```
  * To get the IP address of the VM where mysql is running
  		`boot2docker ip`
  * Checking the mysql logs
  		`docker logs -f auth-db`
  * Note that sometime the server goes down maybe because your m/c sleeps and this cause the mysql container to go bad.
    * In this case first stop the docker container by typing
        `docker stop auth-db`	
    * Run the below command to remove stopped container.
        `docker rm $(docker ps -a -q)`
    * Start the mysql container again by using the `docker run -d -e........` command specified above.
             			
### Projects that need to be started before
* [config server](config-server/README.md) - For pulling the configuration information
* [webserver-registry](webserver-registry/README.md) - For starting the Eureka server since the authorization server also is a micro-service that needs to be registered with Eureka server.           	

### Running the application
* Build the application by running the `./gradlew clean build` gradle command at the "auth-server" project root folder	on the terminal.
* Run the application using either of the 2 below
  * If you want to run the application in debug mode, then `./gradlew bootRun`; this would start the debugger at port 4100 that can be connected as remote java application.
  * If you want to run the application as jar file, then `java -jar build/libs/sample-auth-server-0.0.1.jar`
  * When testing the authentication and authorization flow, ensure that you don't have cookies and HTTP basic credentials stored in the browser cache. The simplest way to do that say in `Chrome` is to open a new `incognito window`.
  
## External Configuration
Please refer to [user webservice](user-webservice/README.md) for details on how the external configuration works. Note that there is separate configuration file for each Spring application; the application should refer to it's own .yml file for configuration.

### Testing different authorization grant types  	
#### Authorization code 
  * This flow is typically used by web server apps(server-to-server communication) to authorize the user and then get the token using POST from the server.
  * The user needs to be authenticated (if required), before the request is sent to the authorization server.
  * The authentication credentials are user `dave` and password `secret`. You can add more user if required in the `com.rohitghatol.microservice.auth.config.OAuthConfiguration` class; look at the `AuthenticationManagerConfiguration` inner class for user initialization.
  * After opening an incognito window, paste the following URL(**Note: response_type=code**) in the browser bar
 		```
 		http://localhost:8899/userauth/oauth/authorize?response_type=code&client_id=client&redirect_uri=http://localhost:8090/index.html
 		```
    * Provide authentication information user `dave` and password `secret`.
    * Click on the "Authorize" button to provide permission for the OAuth server to provide token to the client.
    * If you have the [web-portal](../web-portal/README.md) project running, then you should land on the index page with the OAuth code in the URL; something like `http://localhost:8090/index.html?code=5s3OgY#/`
    * Once you have the access code, you can get the actual OAuth access token by making the following POST request using curl.
    ```
    $ curl client:secret@localhost:8899/userauth/oauth/token \
    -d grant_type=authorization_code -d client_id=client \
    -d redirect_uri=http://localhost:8090/index.html -d code=5s3OgY
    ```
    * Response received would be something like
    ```
    {"access_token":"5a3feb70-8ee8-49fd-af25-528259c8cffd","token_type":"bearer","refresh_token":"5f578b3e-5301-4995-9f7f-5473784d0184","expires_in":29,"scope":"read"}
	```
	
#### Implicit
  * Implicit grants are used in browser based application when we can't show the client secret on the browser side.
  * After opening an incognito window, paste the following URL(**Note: response_type=token**) in the browser bar. 
  	```
  	http://localhost:8899/userauth/oauth/authorize?response_type=token&client_id=client&redirect_uri=http://localhost:8090/index.html
  	```
   * Provide authentication information user `dave` and password `secret`.
   * Click on the "Authorize" button to provide permission for the OAuth server to provide token to the client.
  * The response redirect us to the redirect website with the access token in the query string.
  	```	
  	http://localhost:8090/index.html#/access_token=52b48575-f5af-42b7-80bd-6ba69a6297fd&token_type=bearer&expires_in=29&scope=read   
   	```
   	 
#### Password
  * The password grant is used to provide the username and password to the authorization server and get the access token directly. 
  * It typically would be used by mobile/desktop application that use a service to get the access token and have implicit access to the user's credentials.
  * Use a client like postman chrome extension to make the POST request for password grant (**Note: grant_type=password**).
  		```
     	http://localhost:8899/userauth/oauth/token?grant_type=password&username=dave&password=secret&redirect_uri=http://localhost:8090/index.html
     	```
     	```
     	Use basic authentication in postman and provide the client and client_secret for the basic authentication
     	Username - client
     	Password - secret
     	``` 
  * The following curl command can be used to verify password grant
  	```
  	$ curl --request POST -u client:secret "http://localhost:8899/userauth/oauth/token?grant_type=password&username=dave&password=secret"
  	```
  * The response received is
  	```	
	{"access_token":"61f349c2-2f08-465a-948d-ccbc25e79c7c","token_type":"bearer","refresh_token":"a537af34-a151-4759-94d3-efe1501daf51","expires_in":29,"scope":"read"}
	```
	
#### Client credentials
  * The client credential grant is used by the client themselves to get an access token without the context of the user involved.
  * This might be required if the application wants to do some book keeping activities (like changing the registered url) or gather statistics.
  * Use a client like postman chrome extension to make the POST request (**Note: grant_type=client_credentials**) for client_credentials grant. Note that **No Auth** should be selected for the authentication scheme since we are bypassing the user here.
  ```
  http://localhost:8899/userauth/oauth/token?grant_type=client_credentials&client_id=client&client_secret=secret
  ```
	
#### refresh token
  * By default, the access token provided by the authorization server is short lived and will expire based on the "expires_in" value provided.
  * If you access a protected resource with an expired token, it will respond back by saying that the token has expired.
  * In this scenario, the application can request another access token from the authorization server by using the refresh token.
  * Assume that we already received the access token for password grant, then we can use the following POST request from chrome postman extension. Note that **grant_type=refresh_token** and you need to provide the refresh_token value that was received in the response for the password grant.
  	```
  	http://localhost:8899/userauth/oauth/token?grant_type=refresh_token&client_id=client&refresh_token=a537af34-a151-4759-94d3-efe1501daf51
  	``` 
  	```
     	Use basic authentication in postman and provide the client and client_secret for the basic authentication
     	Username - client
     	Password - secret
    ``` 
    
### Getting protected resources
* Once you have the access token, put the value in a header called "Authorization" and value as "Bearer &lt;access_token_value&gt; and make the request.
	```     	
	curl -H "Authorization: Bearer e30770ef-8e6f80-bd24-39391c9e1453" http://localhost:8081/user	
	```
