/**
 * 
 */
package com.rohitghatol.microservice.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * The Main Spring Boot Application class that starts the authorization
 * server.</br>
 * </br>
 * 
 * Note that the server is also a Eureka client so as to register with the
 * Eureka server and be auto-discovered by other Eureka clients.
 *
 * @author rohitghatol
 */

@ComponentScan
@EnableAutoConfiguration
@EnableEurekaClient
public class Application {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
