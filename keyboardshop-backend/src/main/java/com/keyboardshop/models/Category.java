package com.keyboardshop.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.keyboardshop.enums.Language;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category
{
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String image;
	private String description;

	@Column(unique = true, nullable = false)
	private String slug;

	@OneToMany(mappedBy = "category")
	@JsonManagedReference
	private List<Product> products;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CategoryTranslation> translations;

	public Category() {}

	public long getId()
	{
		return id;
	}

	public Category setId(long id)
	{
		this.id = id;
		return this;
	}

	public String getName()
	{
		return name;
	}

	public Category setName(String name)
	{
		this.name = name;
		return this;
	}

	public String getImage()
	{
		return image;
	}

	public Category setImage(String image)
	{
		this.image = image;
		return this;
	}

	public String getDescription()
	{
		return description;
	}

	public Category setDescription(String description)
	{
		this.description = description;
		return this;
	}

	public String getSlug()
	{
		return slug;
	}

	public Category setSlug(String slug)
	{
		this.slug = slug;
		return this;
	}

	public List<Product> getProducts()
	{
		return products;
	}

	public Category setProducts(List<Product> products)
	{
		this.products = products;
		return this;
	}

    public List<CategoryTranslation> getTranslations()
    {
        return translations;
    }

    public Category setTranslations(List<CategoryTranslation> translations)
    {
        this.translations = translations;
        return this;
    }

    public CategoryTranslation getTranslationByLanguage(Language language)
    {
        for (CategoryTranslation translation : translations) {
            if (translation.getLanguage() == language) {
                return translation;
            }
        }
        return null;
    }
}
