/**
 * 
 */
package com.rohitghatol.microservice.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;


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
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	/**
	 * The Class OAuth2Config.
	 */
	@Configuration
    @EnableAuthorizationServer
    protected static class OAuth2Config extends AuthorizationServerConfigurerAdapter {

            /** The authentication manager. */
            @Autowired
            private AuthenticationManager authenticationManager;

            /* (non-Javadoc)
             * @see org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter#configure(org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer)
             */
            @Override
            public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
                    endpoints.authenticationManager(authenticationManager);
            }

            /* (non-Javadoc)
             * @see org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter#configure(org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer)
             */
            @Override
            public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
                    clients.inMemory()
                        .withClient("client")
                        .authorizedGrantTypes("client_credentials","refresh_token", "password")
                        .authorities("ROLE_CLIENT")
                        .scopes("read")
                        .resourceIds("paywize")
                        .secret("secret");
            }
    }

}
