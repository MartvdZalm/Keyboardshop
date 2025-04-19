package com.keyboardshop.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.keyboardshop.enums.Language;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Product
{
	@Id
	@GeneratedValue
	private long id;
    private String name;

	@Column(columnDefinition = "TEXT")
    private String description;
	private BigDecimal price;
	private int quantity;

	@Column(unique = true, nullable = false)
	private String slug;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductAttribute> attributes;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JsonIgnore
	private Category category;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<ProductImage> images;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<ProductTranslation> translations;

	@Column(name = "is_active", columnDefinition = "boolean default true")
	private boolean active = true;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	public Product() {}

	public long getId()
	{
		return id;
	}

	public Product setId(long id)
	{
		this.id = id;
		return this;
	}

	public String getName()
	{
		return name;
	}

	public Product setName(String name)
	{
		this.name = name;
		return this;
	}

	public String getDescription()
	{
		return description;
	}

	public Product setDescription(String description)
	{
		this.description = description;
		return this;
	}

	public List<ProductAttribute> getAttributes()
	{
		return attributes;
	}

	public Product setAttributes(List<ProductAttribute> attributes)
	{
		this.attributes = attributes;
		return this;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public Product setQuantity(int quantity)
	{
		this.quantity = quantity;
		return this;
	}

	public String getSlug()
	{
		return slug;
	}

	public Product setSlug(String slug)
	{
		this.slug = slug;
		return this;
	}

	public BigDecimal getPrice()
	{
		return price;
	}

	public Product setPrice(BigDecimal price)
	{
		this.price = price;
		return this;
	}

	public long getCategoryId()
	{
	    return category != null ? category.getId() : null;
	}

	public Category getCategory()
	{
		return category;
	}

	public Product setCategory(Category category)
	{
		this.category = category;
		return this;
	}

	public List<ProductImage> getImages()
	{
		return images;
	}

	public Product setImages(List<ProductImage> images)
	{
		this.images = images;
		return this;
	}

	public boolean getActive()
	{
		return active;
	}

	public Product toggleActive()
	{
		this.active = !active;
		return this;
	}

    public List<ProductTranslation> getTranslations()
    {
        return translations;
    }

    public Product setTranslations(List<ProductTranslation> translations)
    {
        this.translations = translations;
        return this;
    }

    public ProductTranslation getTranslationByLanguage(Language language)
    {
        for (ProductTranslation translation : translations) {
            if (translation.getLanguage() == language) {
                return translation;
            }
        }
        return null;
    }	

	public LocalDateTime getCreatedAt()
	{
		return createdAt;
	}

	public Product setCreatedAt()
	{
		this.createdAt = LocalDateTime.now();
		return this;
	}

	public LocalDateTime getUpdatedAt()
	{
		return updatedAt;
	}

	public Product setUpdatedAt()
	{
		this.updatedAt = LocalDateTime.now();
		return this;
	}
}
