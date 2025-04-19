package com.keyboardshop.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.keyboardshop.dao.ProductDAO;
import com.keyboardshop.models.Product;

@Service
public class SearchService
{
	private final ProductDAO productDAO;
	private final TranslationService translationService;

	public SearchService(ProductDAO productDAO, TranslationService translationService)
	{
		this.productDAO = productDAO;
		this.translationService = translationService;
	}

	public List<Product> search(String query, String lang)
	{
		List<Product> products = this.productDAO.search(query);
    	return products.stream()
	        .map(product -> this.translationService.translateProduct(product, lang))
	        .collect(Collectors.toList());	}
}
