package com.keyboardshop.dto;

import com.keyboardshop.validation.ValidPassword;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CustomUserDTO
{
	@NotBlank(message = "Firstname is required")
	@Size(min = 2, max = 100, message = "Firstname must be between 2 and 50 characters")
	private String firstName;

	@NotBlank(message = "Lastname is required")
	@Size(min = 2, max = 100, message = "Lastname must be between 2 and 50 characters")
	private String lastName;

	@NotBlank(message = "Email is required")
	@Email(message = "Email should be valid")
	private String email;

	@NotBlank(message = "Password is required")
	@ValidPassword(message = "Password should be valid")
	private String password;

	private String streetName;
	private String houseNumber;
	private String postalCode;
	private String city;
	private String phoneNumber;

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

	public String getStreetName()
	{
		return streetName;
	}

	public void setStreetName(String streetName)
	{
		this.streetName = streetName;
	}

	public String getHouseNumber()
	{
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber)
	{
		this.houseNumber = houseNumber;
	}

	public String getPostalCode()
	{
		return postalCode;
	}

	public void setPostalCode(String postalCode)
	{
		this.postalCode = postalCode;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}
}
