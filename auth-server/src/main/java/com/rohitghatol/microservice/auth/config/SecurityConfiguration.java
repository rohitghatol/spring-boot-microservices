package com.rohitghatol.microservice.auth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
//@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		
		//auth.inMemoryAuthentication()
		auth.jdbcAuthentication().dataSource(dataSource)
			.withUser("John").roles("ADMIN").password("password")
			.and()
			.withUser("Mary").roles("ADMIN").password("password");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/**")
			.authenticated()
			.and()
			.httpBasic()
			.realmName("OAuth Server");
	}
	

}