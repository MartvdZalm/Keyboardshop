package com.keyboardshop.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.keyboardshop.enums.Language;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ProductTranslation
{
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private Language language;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    private Product product;

    public ProductTranslation() {}

    public long getId()
    {
        return id;
    }

    public ProductTranslation setId(long id)
    {
        this.id = id;
        return this;
    }

    public Language getLanguage()
    {
        return language;
    }

    public ProductTranslation setLanguage(Language language)
    {
        this.language = language;
        return this;
    }

    public String getName()
    {
        return name;
    }

    public ProductTranslation setName(String name)
    {
        this.name = name;
        return this;
    }

    public String getDescription()
    {
        return description;
    }

    public ProductTranslation setDescription(String description)
    {
        this.description = description;
        return this;
    }

    public Product getProduct()
    {
        return product;
    }

    public ProductTranslation setProduct(Product product)
    {
        this.product = product;
        return this;
    }    
}
