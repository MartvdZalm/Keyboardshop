package com.keyboardshop.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.keyboardshop.dto.AuthLoginDTO;
import com.keyboardshop.dto.AuthRegisterDTO;
import com.keyboardshop.dto.LoginResponseDTO;
import com.keyboardshop.services.AuthService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController
{
    private final AuthService authService;

    public AuthController(AuthService authService)
    {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody AuthRegisterDTO authRegisterDTO)
    {
        this.authService.register(authRegisterDTO);
        return ResponseEntity.ok(Map.of("message", "Registration completed."));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody AuthLoginDTO authLoginDTO)
    {
        return ResponseEntity.ok(this.authService.login(authLoginDTO));
    }
}

