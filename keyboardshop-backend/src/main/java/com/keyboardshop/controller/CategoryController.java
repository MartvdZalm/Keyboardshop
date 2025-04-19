package com.keyboardshop.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.keyboardshop.dto.CategoryDTO;
import com.keyboardshop.models.Category;
import com.keyboardshop.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController
{
	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService)
	{
		this.categoryService = categoryService;
	}

	@GetMapping()
	public ResponseEntity<List<Category>> getAllCategories(@RequestParam(defaultValue = "en") String lang)
	{
		return ResponseEntity.ok(this.categoryService.getAll(lang));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable long id, @RequestParam(defaultValue = "en") String lang)
	{
		return ResponseEntity.ok(this.categoryService.getCategoryByIdAndLanguage(id, lang));
	}

	@GetMapping("/slug/{slug}")
	public ResponseEntity<Category> getCategoryBySlug(@PathVariable String slug, @RequestParam(defaultValue = "en") String lang)
	{
		return ResponseEntity.ok(this.categoryService.getCategoryBySlug(slug, lang));
	}

	@PostMapping()
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> createCategory(@Valid @RequestBody CategoryDTO categoryDTO)
	{
		this.categoryService.createCategory(categoryDTO);
		return ResponseEntity.ok("Category created");
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable long id)
	{
		this.categoryService.updateCategory(categoryDTO, id);
		return ResponseEntity.ok("Updated category");
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> deleteCategory(@PathVariable long id)
	{
		this.categoryService.deleteCategory(id);
		return ResponseEntity.ok("Deleted category");
	}
}
