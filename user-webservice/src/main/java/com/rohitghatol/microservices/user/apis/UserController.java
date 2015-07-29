package com.rohitghatol.microservices.user.apis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rohitghatol.microservices.user.dto.UserDTO;

@RestController
@RequestMapping("/")
public class UserController {

	@Value("${mail.domain}")
	private String mailDomain;
	
	private List<UserDTO> users= new ArrayList<UserDTO>();
	
	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
	public List<UserDTO> getUsers(){
		users= new ArrayList<UserDTO>();
		users.add(new UserDTO("firstName1", "lastName1", "1", "first1.last1@"+mailDomain));
		users.add(new UserDTO("firstName2", "lastName2", "2", "first2.last2@"+mailDomain));
		return users;
	}
	
	@RequestMapping(value = "{userName}", method = RequestMethod.GET, headers = "Accept=application/json")
	public UserDTO getUserByUserName(@PathVariable("userName") String userName){
		return new UserDTO("firstNameDetails", "lastNameDetails", "1", "first.last@"+mailDomain);
	}
}
