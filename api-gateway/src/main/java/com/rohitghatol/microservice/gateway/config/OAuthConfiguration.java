package com.rohitghatol.microservice.gateway.config;

import org.springframework.cloud.security.oauth2.sso.OAuth2SsoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;


/**
 * The Class OAuthConfiguration.
 */
@Configuration
@Component
public class OAuthConfiguration extends OAuth2SsoConfigurerAdapter {
	
	@Override
	public void match(RequestMatchers matchers) {
		matchers.anyRequest();
	}
	
	/**
	 * Define the security that applies to the proxy
	 */
	@Override
    public void configure(HttpSecurity http) throws Exception {
        http
        	.authorizeRequests()
        	//Allow access to all static resources without authentication
        	.antMatchers("/","/**/*.html").permitAll()
        	.anyRequest().authenticated()
        	.antMatchers(HttpMethod.GET, "/api/user/**","/api/task/**").access("#oauth2.hasScope('read')")
            .antMatchers(HttpMethod.OPTIONS, "/api/user/**","/api/task/**").access("#oauth2.hasScope('read')")
            .antMatchers(HttpMethod.POST, "/api/user/**","/api/task/**").access("#oauth2.hasScope('write')")
            .antMatchers(HttpMethod.PUT, "/api/user/**","/api/task/**").access("#oauth2.hasScope('write')")
            .antMatchers(HttpMethod.PATCH, "/api/user/**","/api/task/**").access("#oauth2.hasScope('write')")
            .antMatchers(HttpMethod.DELETE, "/api/user/**","/api/task/**").access("#oauth2.hasScope('write')");
    }
}
