package com.keyboardshop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
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

import com.keyboardshop.dto.PaginatedResponseDTO;
import com.keyboardshop.dto.ProductDTO;
import com.keyboardshop.models.Product;
import com.keyboardshop.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController
{
	private final ProductService productService;

	public ProductController(ProductService productService)
	{
		this.productService = productService;
	}

	@GetMapping()
	public ResponseEntity<List<Product>> getAllProducts(@RequestParam(defaultValue = "en") String lang)
	{
		return ResponseEntity.ok(this.productService.getAllProducts(lang));
	}

	@GetMapping("/{categoryId}/pagination")
	public ResponseEntity<PaginatedResponseDTO<Product>> getPaginatedProductsByCategory(
	        @PathVariable long categoryId,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "21") int size,
	    	@RequestParam(defaultValue = "en") String lang)
	{
	    Page<Product> productPage = this.productService.getPaginatedProductsByCategory(categoryId, page, size, lang);
	    PaginatedResponseDTO<Product> paginatedResponseDTO = new PaginatedResponseDTO<Product>(
	        productPage.getContent(),
	        productPage.getNumber(),
	        productPage.getSize(),
	        productPage.getTotalElements(),
	        productPage.getTotalPages()
	    );

	    return ResponseEntity.ok(paginatedResponseDTO);
	}

	@GetMapping("/pagination")
	public ResponseEntity<PaginatedResponseDTO<Product>> getPaginatedProducts(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "21") int size,
	        @RequestParam(defaultValue = "en") String lang)
	{
	    Page<Product> productPage = this.productService.getPaginatedProducts(page, size, lang);
	    PaginatedResponseDTO<Product> paginatedResponseDTO = new PaginatedResponseDTO<Product>(
	        productPage.getContent(),
	        productPage.getNumber(),
	        productPage.getSize(),
	        productPage.getTotalElements(),
	        productPage.getTotalPages()
	    );

	    return ResponseEntity.ok(paginatedResponseDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable long id, @RequestParam(defaultValue = "en") String lang)
	{
	    return ResponseEntity.ok(this.productService.getProductById(id, lang));
	}

	@GetMapping("/slug/{slug}")
	public ResponseEntity<Product> getProductBySlug(@PathVariable String slug, @RequestParam(defaultValue = "en") String lang)
	{
	    return ResponseEntity.ok(this.productService.getProductBySlug(slug, lang));
	}

	@PostMapping()
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Map<String, String>> createProduct(@Valid @RequestBody ProductDTO productDTO)
	{
		this.productService.createProduct(productDTO);
		return ResponseEntity.ok(Map.of("message", "Product is created."));
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Map<String, String>> updateProduct(@Valid @RequestBody ProductDTO productDTO, @PathVariable long id)
	{
		this.productService.updateProduct(productDTO, id);
		return ResponseEntity.ok(Map.of("message", "Product is successfully updated."));
	}

	@PutMapping("/active/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Map<String, String>> updateActiveProduct(@PathVariable long id)
	{
		this.productService.updateActiveProduct(id);
		return ResponseEntity.ok(Map.of("message", "Product updated!"));
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable long id)
	{
		this.productService.deleteProduct(id);
		return ResponseEntity.ok(Map.of("message", "Product deleted"));
	}
}
