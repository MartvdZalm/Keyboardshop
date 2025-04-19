package com.keyboardshop.dto;

import com.keyboardshop.validation.ValidPassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AuthRegisterDTO
{
	@NotBlank(message = "Firstname is required")
	@Size(min = 2, max = 50, message = "Firstname must be between 2 and 50 characters")
	private String firstName;

	@NotBlank(message = "Lastname is required")
	@Size(min = 2, max = 50, message = "Lastname must be between 2 and 50 characters")
	private String lastName;

	@NotBlank(message = "Email is required")
	@Email(message = "Email should be valid")
	private String email;

	@NotBlank(message = "Password is required")
	@ValidPassword(message = "Password should be valid")
	private String password;

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
}
