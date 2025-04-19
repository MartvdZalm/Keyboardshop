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
public class ProductAttributeTranslation
{
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private Language language;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String value;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    private ProductAttribute productAttribute;

    public ProductAttributeTranslation() {}

    public ProductAttributeTranslation(String name, String value)
    {
        this.name = name;
        this.value = value;
    }

    public long getId()
    {
        return id;
    }

    public ProductAttributeTranslation setId(long id)
    {
        this.id = id;
        return this;
    }

    public Language getLanguage()
    {
        return language;
    }

    public ProductAttributeTranslation setLanguage(Language language)
    {
        this.language = language;
        return this;
    }

    public String getName()
    {
        return name;
    }

    public ProductAttributeTranslation setName(String name)
    {
        this.name = name;
        return this;
    }

    public String getValue()
    {
        return value;
    }

    public ProductAttributeTranslation setValue(String value)
    {
        this.value = value;
        return this;
    }

    public ProductAttribute getProductAttribute()
    {
        return productAttribute;
    }

    public ProductAttributeTranslation setProductAttribute(ProductAttribute productAttribute)
    {
        this.productAttribute = productAttribute;
        return this;
    }
}
