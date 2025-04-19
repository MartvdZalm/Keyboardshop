package com.keyboardshop.utils.seeders;

import org.springframework.stereotype.Component;

import com.keyboardshop.models.CustomUser;
import com.keyboardshop.repository.CustomUserRepository;

@Component
public class UserSeeder
{
    private final CustomUserRepository customUserRepository;

    public UserSeeder(CustomUserRepository customUserRepository)
    {
        this.customUserRepository = customUserRepository;
    }

    public void seed()
    {
        CustomUser admin = new CustomUser()
            .setFirstName("Mart")
            .setLastName("van der Zalm")
            .setEmail("admin@keyhub.com")
            .setPassword("$2a$10$w6W9VrqtrxB73tUr4Eowru4AflP37rQKRLuqsQUykvtKeJoOCK/U2")
            .setRole("ADMIN")
            .setStreetName("Something Street")
            .setHouseNumber("23")
            .setPostalCode("3464KH")
            .setCity("Noordwijk")
            .setPhoneNumber("06 32161452");

        this.customUserRepository.save(admin);    
    }
}
