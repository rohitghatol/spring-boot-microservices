package com.rohitghatol.microservice.auth.api;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST endpoint to be used by other micro-services using SSO to validate the
 * authentication of the logged in user.
 * 
 * @author anilallewar
 *
 */
@RestController
@RequestMapping("/")
public class AuthUserController {
	
	/**
	 * Return the principal identifying the logged in user
	 * @param user
	 * @return
	 */
	@RequestMapping("/me")
	public Principal getCurrentLoggedInUser(Principal user) {
		return user;
	}
}
