/**
 * 
 */
package com.rohitghatol.microservices.comments.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Resource server configuration defining what endpoints are protected.
 * 
 * @author anilallewar
 *
 */
@Configuration
@EnableResourceServer
public class CommentsConfiguration extends ResourceServerConfigurerAdapter {

	/**
	 * Provide security so that endpoints are only served if the request is
	 * already authenticated.
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.requestMatchers()
			.antMatchers("/**")
		.and()
			.authorizeRequests()
				.anyRequest()
					.authenticated()
					.antMatchers(HttpMethod.GET, "/**").access("#oauth2.hasScope('read')")
		            .antMatchers(HttpMethod.OPTIONS, "/**").access("#oauth2.hasScope('read')")
		            .antMatchers(HttpMethod.POST, "/**").access("#oauth2.hasScope('write')")
		            .antMatchers(HttpMethod.PUT, "/**").access("#oauth2.hasScope('write')")
		            .antMatchers(HttpMethod.PATCH, "/**").access("#oauth2.hasScope('write')")
		            .antMatchers(HttpMethod.DELETE, "/**").access("#oauth2.hasScope('write')");
		// @formatter:on
	}

	/**
	 * Id of the resource that you are letting the client have access to.
	 * Supposing you have another api ("say api2"), then you can customize the
	 * access within resource server to define what api is for what resource id.
	 * <br>
	 * <br>
	 * 
	 * So suppose you have 2 APIs, then you can define 2 resource servers.
	 * <ol>
	 * <li>Client 1 has been configured for access to resourceid1, so he can
	 * only access "api1" if the resource server configures the resourceid to
	 * "api1".</li>
	 * <li>Client 1 can't access resource server 2 since it has configured the
	 * resource id to "api2"
	 * </li>
	 * </ol>
	 * 
	 */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
         resources.resourceId("apis");
    }
}
