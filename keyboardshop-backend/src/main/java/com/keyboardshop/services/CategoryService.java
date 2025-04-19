package com.keyboardshop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.keyboardshop.dao.CategoryDAO;
import com.keyboardshop.dao.ProductDAO;
import com.keyboardshop.dto.CategoryDTO;
import com.keyboardshop.models.Category;
import com.keyboardshop.models.Product;
import com.keyboardshop.utils.SlugUtil;


@Service
public class CategoryService
{
	private final CategoryDAO categoryDAO;
	private final ProductDAO productDAO;
	private final TranslationService translationService;

	public CategoryService(CategoryDAO categoryDAO, ProductDAO productDAO, TranslationService translationService)
	{
		this.categoryDAO = categoryDAO;
		this.productDAO = productDAO;
		this.translationService = translationService;
	}

	public List<Category> getAll(String lang)
	{
        return this.categoryDAO.getAll()
			.stream()
			.map((category) -> this.translationService.translateCategory(category, lang))
			.collect(Collectors.toList());
	}

	public Category getCategoryById(long id)
	{
		return this.categoryDAO.get(id);
	}

	public Category getCategoryByIdAndLanguage(long id, String lang)
	{
	    Category category = this.categoryDAO.get(id);
	    return this.translationService.translateCategory(category, lang);
	}

	public Category getCategoryBySlug(String slug, String lang)
	{
	    Category category = this.categoryDAO.getBySlug(slug);
	    return this.translationService.translateCategory(category, lang);
	}

	public void createCategory(CategoryDTO categoryDTO)
	{
		if (this.categoryDAO.getIfExistByName(categoryDTO.getName()).isPresent()) {
		    throw new ResponseStatusException(
		    	HttpStatus.BAD_REQUEST,
		    	"Category with this name already exists."
		    );
		}

		Category category = new Category()
			.setName(categoryDTO.getName())
			.setImage(categoryDTO.getImage())
			.setDescription(categoryDTO.getDescription())
			.setSlug(SlugUtil.toSlug(categoryDTO.getName()));

		List<Long> productIds = categoryDTO.getProductIds();
		for (Long productId : productIds) {
			Product product = this.productDAO.get(productId);
			product.setCategory(category);
		}

		this.categoryDAO.create(category);
	}

	public void updateCategory(CategoryDTO categoryDTO, long id)
	{
		Category category = this.getCategoryById(id);

		if (
			!category.getName().equals(categoryDTO.getName()) &&
			this.categoryDAO.getIfExistByName(categoryDTO.getName()).isPresent()
		) {
		    throw new ResponseStatusException(
		    	HttpStatus.BAD_REQUEST,
		    	"Category with this name already exists."
		    );
		}

		category.setName(categoryDTO.getName())
			.setImage(categoryDTO.getImage())
			.setDescription(categoryDTO.getDescription())
			.setSlug(SlugUtil.toSlug(categoryDTO.getName()));

    	List<Product> existingProducts = new ArrayList<>(category.getProducts());

		for (Long productId : categoryDTO.getProductIds()) {
			boolean productExist = existingProducts.stream()
				.anyMatch((product) -> Objects.equals(product.getId(), productId));

			if (!productExist) {
				Product product = this.productDAO.get(productId);
				product.setCategory(category);
				category.getProducts().add(product);
			}
		}

	    existingProducts.stream()
	        .filter((product) -> !categoryDTO.getProductIds().contains(product.getId()))
	        .forEach((product) -> {
	            product.setCategory(null);
	            category.getProducts().remove(product);
	        });

    	this.categoryDAO.update(category);
	}

	public void deleteCategory(long id)
	{
		this.categoryDAO.get(id);
		this.categoryDAO.delete(id);
	}
}
