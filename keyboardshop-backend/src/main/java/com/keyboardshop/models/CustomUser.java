package com.keyboardshop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "custom_user")
public class CustomUser
{
	@Id
	@GeneratedValue
	private long id;
	private String firstName;
    private String lastName;
	private String email;

	@JsonIgnore
	private String password;

	private String role;
	private String streetName;
	private String houseNumber;
	private String postalCode;
	private String city;
	private String phoneNumber;

	public CustomUser() {}

	public long getId()
	{
		return id;
	}

	public CustomUser setId(long id)
	{
		this.id = id;
		return this;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public CustomUser setFirstName(String firstName)
	{
		this.firstName = firstName;
		return this;
	}

	public String getLastName()
	{
		return lastName;
	}
	public CustomUser setLastName(String lastName)
	{
		this.lastName = lastName;
		return this;
	}

	public String getEmail()
	{
		return email;
	}

	public CustomUser setEmail(String email)
	{
		this.email = email;
		return this;
	}

	public String getPassword()
	{
		return password;
	}

	public CustomUser setPassword(String password)
	{
		this.password = password;
		return this;
	}

	public String getRole()
	{
		return role;
	}

	public CustomUser setRole(String role)
	{
		this.role = role;
		return this;
	}

	public String getStreetName()
	{
		return streetName;
	}

	public CustomUser setStreetName(String streetName)
	{
		this.streetName = streetName;
		return this;
	}

	public String getHouseNumber()
	{
		return houseNumber;
	}

	public CustomUser setHouseNumber(String houseNumber)
	{
		this.houseNumber = houseNumber;
		return this;
	}

	public String getPostalCode()
	{
		return postalCode;
	}

	public CustomUser setPostalCode(String postalCode)
	{
		this.postalCode = postalCode;
		return this;
	}

	public String getCity()
	{
		return city;
	}

	public CustomUser setCity(String city)
	{
		this.city = city;
		return this;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public CustomUser setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
		return this;
	}
}
