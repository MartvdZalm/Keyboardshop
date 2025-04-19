package com.keyboardshop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.keyboardshop.models.Product; 

public interface ProductRepository extends JpaRepository<Product, Long>
{
	@Query("SELECT p FROM Product p WHERE p.category.id = :categoryId AND p.active = true")
    public Page<Product> findAllByCategoryId(@Param("categoryId") long categoryId, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.active = true")
    public Page<Product> findAllPagenated(Pageable pageable);

    @Query("""
        SELECT DISTINCT p FROM Product p
        JOIN p.translations t
        WHERE LOWER(t.name) LIKE LOWER(CONCAT('%', :query, '%'))
    """)
    public List<Product> searchByTranslation(@Param("query") String query);

	// public List<Product> findByNameContainingIgnoreCase(String name);

	public Optional<Product> findBySlug(String slug);
}
