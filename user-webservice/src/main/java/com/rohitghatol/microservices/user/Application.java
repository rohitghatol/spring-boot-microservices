/**
 * 
 */
package com.rohitghatol.microservices.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.security.oauth2.resource.EnableOAuth2Resource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author rohitghatol
 *
 */
@EnableAutoConfiguration
@ComponentScan
@Configuration
@EnableEurekaClient
@EnableOAuth2Resource
public class Application {
	public static void main(String[] args) {

		SpringApplication.run(Application.class,args);
		
	}
}
