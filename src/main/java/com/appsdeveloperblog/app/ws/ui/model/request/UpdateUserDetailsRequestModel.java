package com.appsdeveloperblog.app.ws.ui.model.request;

/**
 * @author Abudé Bittar
 */


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


// the @Valid class --> Hibernate

public class UpdateUserDetailsRequestModel {
	
    // just searching for Hibernate bean validations
	
	
	@NotNull(message="First name cannot be null")
	@Size(min=2, message = "First name must not be less than 2 characters")
	private String firstName;
	
	@NotNull(message="Last name cannot be null")
	@Size(min=2, message = "Last name must not be less than 2 characters")
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
