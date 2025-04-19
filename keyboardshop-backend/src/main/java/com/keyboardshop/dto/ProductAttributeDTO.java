package com.keyboardshop.dto;

import jakarta.validation.constraints.NotBlank;

public class ProductAttributeDTO
{
	@NotBlank(message = "Attribute name is required")
    private String name;

    @NotBlank(message = "Attribute value is required")
    private String value;

    public String getName()
    {
    	return name;
    }

    public void setName(String name)
    {
    	this.name = name;
    }

    public String getValue()
    {
    	return value;
    }

    public void setValue(String value)
    {
    	this.value = value;
    }
}
