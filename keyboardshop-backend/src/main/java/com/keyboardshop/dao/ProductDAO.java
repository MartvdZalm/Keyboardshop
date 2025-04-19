package com.keyboardshop.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.keyboardshop.models.Product;
import com.keyboardshop.repository.ProductRepository;

@Component
public class ProductDAO implements DAO<Product>
{
	private final ProductRepository productRepository;

	public ProductDAO(ProductRepository productRepository)
	{
		this.productRepository = productRepository;
	}

    public Product get(long id)
    {
        return this.productRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Product with the id " + id + " not found."
            ));
    }

    public List<Product> getAll()
    {
        return this.productRepository.findAll();
    }

    public Page<Product> getPaginatedByCategory(Pageable pageable, long id)
    {
        return this.productRepository.findAllByCategoryId(id, pageable);
    }

    public Page<Product> getPaginated(Pageable pageable)
    {
        return this.productRepository.findAllPagenated(pageable);
    }

    public Product getBySlug(String slug)
    {
        return this.productRepository.findBySlug(slug)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Product with the slug " + slug + " not found."
            ));
    }

    public Optional<Product> getExistsBySlug(String slug)
    {
        return this.productRepository.findBySlug(slug);
    }

    public void create(Product product)
    {
        this.productRepository.save(product);
    }

    public void update(Product product)
    {
        this.productRepository.save(this.get(product.getId()));
    }

    public void delete(long id)
    {
        this.productRepository.deleteById(id);
    }

    public List<Product> search(String query)
    {
        return this.productRepository.searchByTranslation(query);
    }
}
