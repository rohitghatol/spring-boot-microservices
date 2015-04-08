package com.rohitghatol.microservice.gateway.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;


/**
 * The Class OAuthConfiguration.
 */
@Configuration
@EnableResourceServer
public class OAuthConfiguration extends ResourceServerConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;

	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter#configure(org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer)
	 */
	@Override
	public void configure(ResourceServerSecurityConfigurer resources)
			throws Exception {
		resources.resourceId("apis").tokenStore(tokenStore());
	}
	@Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/api/user/**","/api/task/**").access("#oauth2.hasScope('read')")
            .antMatchers(HttpMethod.OPTIONS, "/api/user/**","/api/task/**").access("#oauth2.hasScope('read')")
            .antMatchers(HttpMethod.POST, "/api/user/**","/api/task/**").access("#oauth2.hasScope('write')")
            .antMatchers(HttpMethod.PUT, "/api/user/**","/api/task/**").access("#oauth2.hasScope('write')")
            .antMatchers(HttpMethod.PATCH, "/api/user/**","/api/task/**").access("#oauth2.hasScope('write')")
            .antMatchers(HttpMethod.DELETE, "/api/user/**","/api/task/**").access("#oauth2.hasScope('write')");
    }
}
