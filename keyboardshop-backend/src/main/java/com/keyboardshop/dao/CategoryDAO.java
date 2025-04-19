package com.keyboardshop.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.keyboardshop.models.Category;
import com.keyboardshop.repository.CategoryRepository;

@Component
public class CategoryDAO implements DAO<Category>
{
	private final CategoryRepository categoryRepository;

	public CategoryDAO(CategoryRepository categoryRepository)
	{
		this.categoryRepository = categoryRepository;
	}

	public Category get(long id)
	{
		return this.categoryRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(
				HttpStatus.NOT_FOUND,
				"Category with the id " + id + " not found."
			));
	}

	public List<Category> getAll()
	{
		return this.categoryRepository.findAll();
	}

	public Optional<Category> getIfExistByName(String name)
	{
		return this.categoryRepository.findByName(name);
	}

	public Category getBySlug(String slug)
	{
		return this.categoryRepository.findBySlug(slug)
			.orElseThrow(() -> new ResponseStatusException(
				HttpStatus.NOT_FOUND,
				"Category with the slug " + slug + " not found."
			));
	}

	public void create(Category category)
	{
		this.categoryRepository.save(category);
	}

	public void update(Category category)
	{
		this.categoryRepository.save(this.get(category.getId()));
	}

	public void delete(long id)
	{
		this.categoryRepository.deleteById(id);
	}
}
