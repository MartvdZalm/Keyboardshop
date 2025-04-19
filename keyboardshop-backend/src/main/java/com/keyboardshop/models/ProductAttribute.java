package com.keyboardshop.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.keyboardshop.enums.Language;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class ProductAttribute
{
    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String value;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JsonBackReference
    private Product product;

    @OneToMany(mappedBy = "productAttribute", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<ProductAttributeTranslation> translations;

    public ProductAttribute() {}

    public ProductAttribute(String name, String value)
    {
        this.name = name;
        this.value = value;
    }

    public long getId()
    {
        return id;
    }

    public ProductAttribute setId(long id)
    {
        this.id = id;
        return this;
    }

    public String getName()
    {
        return name;
    }

    public ProductAttribute setName(String name)
    {
        this.name = name;
        return this;
    }

    public String getValue()
    {
        return value;
    }

    public ProductAttribute setValue(String value)
    {
        this.value = value;
        return this;
    }

    public Product getProduct()
    {
        return product;
    }

    public ProductAttribute setProduct(Product product)
    {
        this.product = product;
        return this;
    }

    public List<ProductAttributeTranslation> getTranslations()
    {
        return translations;
    }

    public ProductAttribute setTranslations(List<ProductAttributeTranslation> translations)
    {
        this.translations = translations;
        return this;
    }

    public ProductAttributeTranslation getTranslationByLanguage(Language language)
    {
        for (ProductAttributeTranslation translation : translations) {
            if (translation.getLanguage() == language) {
                return translation;
            }
        }
        return null;
    }
}
