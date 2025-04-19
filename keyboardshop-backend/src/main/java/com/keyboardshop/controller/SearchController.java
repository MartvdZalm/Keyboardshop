package com.keyboardshop.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.keyboardshop.models.Product;
import com.keyboardshop.services.SearchService;

@RestController
@RequestMapping("/search")
@CrossOrigin(origins = "http://localhost:4200")
public class SearchController
{
	private final SearchService searchService;

	public SearchController(SearchService searchService)
	{
		this.searchService = searchService;
	}

    @GetMapping
    public ResponseEntity<List<Product>> search(@RequestParam("query") String query, @RequestParam(defaultValue = "en") String lang)
    {
        return ResponseEntity.ok(this.searchService.search(query, lang));
    }
}
