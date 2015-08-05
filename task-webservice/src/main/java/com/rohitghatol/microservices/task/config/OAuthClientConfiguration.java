package com.rohitghatol.microservices.task.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

/**
 * Configuration that sets up the OAuth2 client operation for making calls to
 * the comments-webservice.
 * 
 * @author anilallewar
 *
 */
@Configuration
@EnableOAuth2Client
public class OAuthClientConfiguration {

	@Value("${spring.oauth2.client.userAuthorizationUri}")
	private String authorizeUrl;

	@Value("${spring.oauth2.client.accessTokenUri}")
	private String tokenUrl;

	@Value("${spring.oauth2.client.clientId}")
	private String clientId;

	/**
	 * RestTempate that relays the OAuth2 token passed to the task webservice.
	 * 
	 * @param oauth2ClientContext
	 * @return
	 */
	@Bean
	public OAuth2RestOperations restTemplate(OAuth2ClientContext oauth2ClientContext) {
		return new OAuth2RestTemplate(resource(), oauth2ClientContext);
	}

	/**
	 * Setup details where the OAuth2 server is.
	 * @return
	 */
	@Bean
	protected OAuth2ProtectedResourceDetails resource() {
		AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();
		resource.setAccessTokenUri(tokenUrl);
		resource.setUserAuthorizationUri(authorizeUrl);
		resource.setClientId(clientId);
		resource.setClientSecret("secret");
		return resource;
	}
}
