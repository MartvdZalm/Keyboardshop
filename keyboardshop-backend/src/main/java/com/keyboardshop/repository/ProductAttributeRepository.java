package com.keyboardshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keyboardshop.models.ProductAttribute;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Long>
{

}
