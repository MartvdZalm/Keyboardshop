package com.keyboardshop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class GuestOrderDTO extends BaseOrderDTO
{   
    @NotBlank(message = "Email is required for guest orders")
    @Email(message = "Email should be valid")
    private String email; 

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
