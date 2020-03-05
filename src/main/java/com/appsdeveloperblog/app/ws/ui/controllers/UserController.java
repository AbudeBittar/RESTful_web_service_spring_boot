package com.appsdeveloperblog.app.ws.ui.controllers;

/**
 * @author Abudé Bittar
 */

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloperblog.app.ws.ui.model.response.UserRest;
import com.appsdeveloperblog.app.ws.userservice.UserService;


/**
 * will register this class as the rest controller and will be able to receive Http requests.
 */
@RestController
/**
 * -- this controller will be responsible for all operations on URL.
 * -- http://locaalhost:8080/users
 */
@RequestMapping("/users")
public class UserController {

	Map<String, UserRest> users;
	
	// @Autowire (let spring framework create an instance of the UserService and it'll inject that instance into this UserController )
	@Autowired
	UserService userService;
	
	// ------------------------------------- GET Method --------------------------------------//
	@GetMapping
	public String getUsers(@RequestParam(value="page", defaultValue="1") int page, 
			@RequestParam(value="limit", defaultValue="50") int limit,
			@RequestParam(value="sort", defaultValue = "desc", required = false) String sort)
	{
		return "get users was called with page = " + page + " and limit = " + limit + " and sort = " + sort;
	}
	
	// ------------------------------------- GET Method --------------------------------------//
	@GetMapping(path="/{userId}", 
			produces =  { 
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
					} )
	 // with MediaType.APPLICATION .... we can specify the format of the output data.
	public ResponseEntity<UserRest> getUser(@PathVariable String userId)
	{
		if(users.containsKey(userId))
		{
			return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	// ---------------------------------------- POST Method ------------------------------------//
	@PostMapping(
			consumes =  { 
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
			}, 
			produces =  { 
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
					}  )
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails)
	{
		//------ Applying dependency injection from different classes and interfaces --------
		
		// DON'T USE NEW KEY WORD
		// User returnedValue = new UserServiceImplimentation().createUser(userDetails);

		//we don't used new key word, so we can test the method independent from other classes.
		UserRest returnValue = userService.createUser(userDetails);
		
		// so createUser() has been used independent ... yahoaa 
		
		return new ResponseEntity<UserRest>(returnValue, HttpStatus.OK);
	}
	
	
	
	// ------------------------------------- PUT Method --------------------------------------//
	@PutMapping(path="/{userId}", consumes =  { 
			MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE
			}, 
			produces =  { 
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
					}  )
	public UserRest updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetailsRequestModel userDetails)
	{
		// Updating the new user details
		 UserRest storedUserDetails = users.get(userId);
		 storedUserDetails.setFirstName(userDetails.getFirstName());
		 storedUserDetails.setLastName(userDetails.getLastName());
		 
		// Updating the users map
		 users.put(userId, storedUserDetails);
		 
		 return storedUserDetails;
	}
	
	
	// ------------------------------------- PUT Method --------------------------------------//
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id)
	{
		users.remove(id);
		
		
		// is deleted, so we don't have any content more
		return ResponseEntity.noContent().build();
	}
}
