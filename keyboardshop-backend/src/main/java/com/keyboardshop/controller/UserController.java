package com.keyboardshop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keyboardshop.dto.CustomUserDTO;
import com.keyboardshop.models.CustomUser;
import com.keyboardshop.services.CustomUserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController
{
	private final CustomUserService customUserService;

	public UserController(CustomUserService customUserService)
	{
		this.customUserService = customUserService;
	}

	@GetMapping()
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<CustomUser>> getAllUsers()
	{
		return ResponseEntity.ok(this.customUserService.getAll());
	}

    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseEntity<CustomUser> getUserProfile()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        CustomUser customUser = this.customUserService.getUserByEmail(email);
        return ResponseEntity.ok(customUser);
    }

	@PutMapping
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	public ResponseEntity<Map<String, String>> updateUser(@Valid @RequestBody CustomUserDTO userDTO)
	{
	    this.customUserService.updateUser(userDTO);
	    return ResponseEntity.ok(Map.of("message", "User updated."));
	}
}
