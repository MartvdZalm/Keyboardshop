package com.keyboardshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keyboardshop.models.CustomUser; 

public interface CustomUserRepository extends JpaRepository<CustomUser, Long>
{
	public Optional<CustomUser> findByEmail(String email);
}
