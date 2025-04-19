package com.keyboardshop.dto;

import jakarta.validation.constraints.NotNull;

public class OrderProductDTO
{
	@NotNull(message = "Product ID is required")
	private Long productId;
	@NotNull(message = "Quantity is required")
	private int quantity;

	public Long getProductId()
	{
		return productId;
	}

	public void setProductId(Long productId)
	{
		this.productId = productId;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
}
