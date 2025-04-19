package com.keyboardshop.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ProductDTO
{
    @NotEmpty(message = "At least one translation is required")
    private List<ProductTranslationDTO> translations;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private BigDecimal price;

    @NotNull(message = "Quantity is required")
    private int quanty;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    private List<String> images = new ArrayList<>();
    private List<ProductAttributeDTO> attributes;

	public List<ProductTranslationDTO> getTranslations()
	{
		return translations;
	}

	public void setTranslations(List<ProductTranslationDTO> translations)
	{
		this.translations = translations;
	}

	public BigDecimal getPrice()
	{
		return price;
	}

	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}

	public int getQuantity()
	{
		return quanty;
	}

	public void setQuantity(int quanty)
	{
		this.quanty = quanty;
	}

	public Long getCategoryId()
	{
		return categoryId;
	}

	public void setCategoryId(Long categoryId)
	{
		this.categoryId = categoryId;
	}

    public List<String> getImages()
    {
    	return images;
    }

    public void setImages(List<String> images)
    {
    	this.images = images;
    }

   	public List<ProductAttributeDTO> getAttributes()
   	{
   		return attributes;
   	}

    public void setAttributes(List<ProductAttributeDTO> attributes)
    {
    	this.attributes = attributes;
    } 
}
