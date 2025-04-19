package com.keyboardshop.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.keyboardshop.dao.CustomUserDAO;
import com.keyboardshop.dto.CustomUserDTO;
import com.keyboardshop.models.CustomUser;

@Service
public class CustomUserService
{
	private final CustomUserDAO customUserDAO;
	private final PasswordEncoder passwordEncoder;

	public CustomUserService(CustomUserDAO customUserDAO, PasswordEncoder passwordEncoder)
	{
		this.customUserDAO = customUserDAO;
		this.passwordEncoder = passwordEncoder;
	}

	public List<CustomUser> getAll()
	{
		return this.customUserDAO.getAll();
	}

	public CustomUser getUserById(long id)
	{
		return this.customUserDAO.get(id);
	}

	public CustomUser getUserByEmail(String email)
	{
		return this.customUserDAO.getByEmail(email);
	}

	public void updateUser(CustomUserDTO userDTO)
	{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        if (!email.equals(userDTO.getEmail()) && this.customUserDAO.getIfExistByEmail(userDTO.getEmail()).isPresent()) {
		    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already in use.");
		}

		CustomUser user = this.getUserByEmail(email);
		
		if (!this.passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
		    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect password");
		}

		String encodedPassword = this.passwordEncoder.encode(userDTO.getPassword());

		user.setFirstName(userDTO.getFirstName())
			.setLastName(userDTO.getLastName())
			.setEmail(userDTO.getEmail())
			.setPassword(encodedPassword)
			.setStreetName(userDTO.getStreetName())
			.setHouseNumber(userDTO.getHouseNumber())
			.setPostalCode(userDTO.getPostalCode())
			.setCity(userDTO.getCity())
			.setPhoneNumber(userDTO.getPhoneNumber());

		this.customUserDAO.update(user);
	}
}
