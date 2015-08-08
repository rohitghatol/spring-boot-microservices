/**
 * 
 */
package com.rohitghatol.microservices.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * The boot application class that defines the spring boot application to have
 * the following properties<br>
 * <br>
 * 
 * <ol>
 * <li>Act as a Eureka client; this behavior is provided by the
 * {@link EnableEurekaClient} annotation. The Eureka server URL is provided by
 * the external configuration provided by the config server.</li>
 * <li>No security is defined for this application since it's the client facing
 * UI application.</li>
 * <li>All the UI artifacts are stored under the <code>"public"</code> folder at
 * the root.</li>
 * </ol>
 * 
 * @author rohitghatol
 *
 */
@EnableAutoConfiguration
@ComponentScan
@Configuration
@EnableEurekaClient
public class Application {
	public static void main(String[] args) {

		SpringApplication.run(Application.class,args);
		
	}
}
