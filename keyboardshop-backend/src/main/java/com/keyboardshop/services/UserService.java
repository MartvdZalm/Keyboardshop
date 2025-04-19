package com.keyboardshop.services;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.keyboardshop.dao.CustomUserDAO;
import com.keyboardshop.models.CustomUser;

@Service
public class UserService implements UserDetailsService
{
	private final CustomUserDAO customUserDAO;

	public UserService(CustomUserDAO customUserDAO)
	{
		this.customUserDAO = customUserDAO;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
	{
		CustomUser customUser = this.customUserDAO.getByEmail(email);

		return new User(
			email,
			customUser.getPassword(),
			Collections.singleton(new SimpleGrantedAuthority(customUser.getRole()))
		);
	}
}
