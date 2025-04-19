package com.keyboardshop.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ProductImage
{
	@Id
	@GeneratedValue
	private long id;
	private String url;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JsonBackReference
	private Product product;

	public ProductImage() {}

	public long getId()
	{
		return id;
	}

	public ProductImage setId(long id)
	{
		this.id = id;
		return this;
	}

	public String getUrl()
	{
		return url;
	}

	public ProductImage setUrl(String url)
	{
		this.url = url;
		return this;
	}

	public Product getProduct()
	{
		return product;
	}

	public ProductImage setProduct(Product product)
	{
		this.product = product;
		return this;
	}
}
