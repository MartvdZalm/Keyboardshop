package com.keyboardshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.keyboardshop.models.Category; 

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>
{
	public Optional<Category> findByName(String name);

	public Optional<Category> findBySlug(String slug);
}
