package com.keyboardshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keyboardshop.models.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long>
{
	// public List<ProductImage> findByProductId(long id);
}
