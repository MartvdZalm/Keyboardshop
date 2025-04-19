package com.keyboardshop.dto;

import jakarta.validation.constraints.NotNull;

public class UserOrderDTO extends BaseOrderDTO
{   
    @NotNull(message = "User ID is required for authenticated orders")
    private Long userId;

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }
}
