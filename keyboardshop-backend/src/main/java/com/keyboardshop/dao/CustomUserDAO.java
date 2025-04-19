package com.keyboardshop.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.keyboardshop.models.CustomUser;
import com.keyboardshop.repository.CustomUserRepository;

@Component
public class CustomUserDAO implements DAO<CustomUser>
{
	private CustomUserRepository customUserRepository;
	
	public CustomUserDAO(CustomUserRepository customUserRepository)
	{
		this.customUserRepository = customUserRepository;
	}

	public CustomUser get(long id)
	{
	    return this.customUserRepository.findById(id)
	        .orElseThrow(() -> new ResponseStatusException(
	            HttpStatus.NOT_FOUND,
	            "User with the id " + id + " not found"
	        ));	
	}

	public CustomUser getByEmail(String email)
	{
		return this.customUserRepository.findByEmail(email)
			.orElseThrow(() -> new ResponseStatusException(
				HttpStatus.NOT_FOUND,
				"Use with the email " + email + " not found"
			));
	}

	public Optional<CustomUser> getIfExistByEmail(String email)
	{
		return this.customUserRepository.findByEmail(email);
	}

	public List<CustomUser> getAll()
	{
		return this.customUserRepository.findAll();
	}

	public void create(CustomUser customUser)
	{
		this.customUserRepository.save(customUser);
	}

	public void update(CustomUser user)
	{
		this.customUserRepository.save(this.get(user.getId()));
	}

	public void delete(long id)
	{
		this.customUserRepository.deleteById(id);
	}
}
