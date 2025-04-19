package com.keyboardshop.dto;

public class LoginResponseDTO
{
	private String email;
	private String token;

	public String getEmail()
	{
		return email;
	}

	public LoginResponseDTO setEmail(String email)
	{
		this.email = email;
		return this;
	}

	public String getToken()
	{
		return token;
	}

	public LoginResponseDTO setToken(String token)
	{
		this.token = token;
		return this;
	}
}
