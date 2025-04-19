package com.keyboardshop.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CategoryDTO
{
	@NotBlank(message = "Name is required")
	@Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
	private String name;

	@NotBlank(message = "Image is required")
	private String image;

	@NotBlank(message = "Description is required")
	@Size(min = 50, max = 500, message = "Description must be between 50 and 500 characters")
	private String description;

	private List<@Positive(message = "Product ID must be a positive number") Long> productIds;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getImage()
	{
		return image;
	}

	public void setImage(String image)
	{
		this.image = image;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public List<Long> getProductIds()
	{
		return productIds;
	}

	public void setProductIds(List<Long> productIds)
	{
		this.productIds = productIds;
	}
}
