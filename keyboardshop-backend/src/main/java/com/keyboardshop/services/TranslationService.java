package com.keyboardshop.services;

import org.springframework.stereotype.Service;

import com.keyboardshop.enums.Language;
import com.keyboardshop.models.Category;
import com.keyboardshop.models.CategoryTranslation;
import com.keyboardshop.models.Product;
import com.keyboardshop.models.ProductTranslation;
import com.keyboardshop.models.ProductAttribute;
import com.keyboardshop.models.ProductAttributeTranslation;

@Service
public class TranslationService
{
    public ProductAttribute translateProductAttribute(ProductAttribute productAttribute, String lang)
    {
        if (productAttribute == null) {
            return null;
        }

        ProductAttributeTranslation translation = productAttribute.getTranslationByLanguage(Language.fromCode(lang));

        if (translation == null) {
            translation = productAttribute.getTranslationByLanguage(Language.EN);
        }

        if (translation != null) {
            productAttribute.setName(translation.getName());
            productAttribute.setValue(translation.getValue());
        }

        return productAttribute;
    }

    public Product translateProduct(Product product, String lang)
    {
        if (product == null) {
            return null;
        }

        ProductTranslation translation = product.getTranslationByLanguage(Language.fromCode(lang));

        if (translation == null) {
            translation = product.getTranslationByLanguage(Language.EN);
        }

        if (translation != null) {
            product.setName(translation.getName());
            product.setDescription(translation.getDescription());
        }

        product.getAttributes().forEach((attribute) -> this.translateProductAttribute(attribute, lang));

        return product;
    }

    public Category translateCategory(Category category, String lang)
    {
        if (category == null) {
            return null;
        }

        CategoryTranslation translation = category.getTranslationByLanguage(Language.fromCode(lang));

        if (translation == null) {
            translation = category.getTranslationByLanguage(Language.EN);
        }

        if (translation != null) {
            category.setName(translation.getName());
            category.setDescription(translation.getDescription());
        }

        category.getProducts().forEach((product) -> this.translateProduct(product, lang));

        return category;
    }
}
