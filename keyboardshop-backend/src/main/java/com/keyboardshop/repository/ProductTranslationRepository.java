package com.keyboardshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keyboardshop.models.ProductTranslation;

public interface ProductTranslationRepository extends JpaRepository<ProductTranslation, Long>
{

}
