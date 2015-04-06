/**
 * 
 */
package com.rohitghatol.microservices.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.ComponentScan;


/**
 * The Main Spring Boot Application class
 *
 * @author rohitghatol
 */
@EnableAutoConfiguration
@EnableConfigServer
@ComponentScan
public class Application {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
