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
public class CategoryTranslation
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
    private Category category;

    public CategoryTranslation() {}

    public long getId()
    {
        return id;
    }

    public CategoryTranslation setId(long id)
    {
        this.id = id;
        return this;
    }

    public Language getLanguage()
    {
        return language;
    }

    public CategoryTranslation setLanguage(Language language)
    {
        this.language = language;
        return this;
    }

    public String getName()
    {
        return name;
    }

    public CategoryTranslation setName(String name)
    {
        this.name = name;
        return this;
    }

    public String getDescription()
    {
        return description;
    }

    public CategoryTranslation setDescription(String description)
    {
        this.description = description;
        return this;
    }

    public Category getCategory()
    {
        return category;
    }

    public CategoryTranslation setCategory(Category category)
    {
        this.category = category;
        return this;
    }    
}
