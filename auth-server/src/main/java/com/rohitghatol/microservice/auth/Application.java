/**
 * 
 */
package com.rohitghatol.microservice.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * The Main Spring Boot Application class.
 *
 * @author rohitghatol
 */

@Configuration
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
