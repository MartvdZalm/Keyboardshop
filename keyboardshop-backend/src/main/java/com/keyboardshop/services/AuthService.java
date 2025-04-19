package com.keyboardshop.services;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.keyboardshop.config.JWTUtil;
import com.keyboardshop.dao.CustomUserDAO;
import com.keyboardshop.dto.AuthLoginDTO;
import com.keyboardshop.dto.AuthRegisterDTO;
import com.keyboardshop.dto.LoginResponseDTO;
import com.keyboardshop.models.CustomUser;

@Service
public class AuthService
{
	private final CustomUserDAO customUserDAO;
	private final AuthenticationManager authManager;
	private final PasswordEncoder passwordEncoder;
	private final JWTUtil jwtUtil;

	public AuthService(CustomUserDAO customUserDAO, AuthenticationManager authManager, PasswordEncoder passwordEncoder, JWTUtil jwtUtil)
	{
		this.customUserDAO = customUserDAO;
		this.authManager = authManager;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
	}

	public void register(AuthRegisterDTO authRegisterDTO)
	{
		Optional<CustomUser> customUser = this.customUserDAO.getIfExistByEmail(authRegisterDTO.getEmail());
		if (customUser.isPresent()) {
		    throw new ResponseStatusException(
		        HttpStatus.BAD_REQUEST,
		        "Email already exists, cannot register with this email"
		    );
		}

		String encodedPassword = this.passwordEncoder.encode(authRegisterDTO.getPassword());

		CustomUser registerdCustomUser = new CustomUser()
			.setFirstName(authRegisterDTO.getFirstName())
			.setLastName(authRegisterDTO.getLastName())
			.setEmail(authRegisterDTO.getEmail())
			.setPassword(encodedPassword)
			.setRole("USER");

		this.customUserDAO.create(registerdCustomUser);
	}

	public LoginResponseDTO login(AuthLoginDTO authLoginDTO)
	{
		try {
			UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(authLoginDTO.getEmail(), authLoginDTO.getPassword());
            this.authManager.authenticate(authInputToken);

            CustomUser customUser = this.customUserDAO.getByEmail(authLoginDTO.getEmail());

            String token = this.jwtUtil.generateToken(customUser.getEmail(), customUser.getRole());

            LoginResponseDTO loginResponse = new LoginResponseDTO()
            	.setEmail(customUser.getEmail())
            	.setToken(token);

            return loginResponse;
		} catch (AuthenticationException e) {
			throw new ResponseStatusException(
				HttpStatus.FORBIDDEN, "No valid credentials"
			);
		}
	}
}
