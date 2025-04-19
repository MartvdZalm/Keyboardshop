package com.keyboardshop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.lang.IllegalArgumentException;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.keyboardshop.dao.CategoryDAO;
import com.keyboardshop.dao.OrderProductDAO;
import com.keyboardshop.dao.ProductDAO;
import com.keyboardshop.dto.ProductAttributeDTO;
import com.keyboardshop.dto.ProductDTO;
import com.keyboardshop.dto.ProductTranslationDTO;
import com.keyboardshop.enums.Language;
import com.keyboardshop.models.Category;
import com.keyboardshop.models.OrderProduct;
import com.keyboardshop.models.Product;
import com.keyboardshop.models.ProductAttribute;
import com.keyboardshop.models.ProductImage;
import com.keyboardshop.models.ProductTranslation;
import com.keyboardshop.utils.SlugUtil;

@Service
public class ProductService
{
	private final ProductDAO productDAO;
	private final CategoryDAO categoryDAO;
	private final OrderProductDAO orderProductDAO;
	private final TranslationService translationService;

	public ProductService(ProductDAO productDAO, CategoryDAO categoryDAO,
		OrderProductDAO orderProductDAO, TranslationService translationService
	)
	{
		this.productDAO = productDAO;
		this.categoryDAO = categoryDAO;
		this.orderProductDAO = orderProductDAO;
		this.translationService = translationService;
	}

	public List<Product> getAllProducts(String lang)
	{
		return this.productDAO.getAll()
			.stream()
			.map((product) -> this.translationService.translateProduct(product, lang))
			.collect(Collectors.toList());
	}

	public Product getProductById(Long id)
	{
		return this.productDAO.get(id);
	}

	public Product getProductById(long id, String lang)
    {
	    Product product = this.productDAO.get(id);
	    return this.translationService.translateProduct(product, lang);
	}

	public Page<Product> getPaginatedProductsByCategory(long categoryId, int page, int size, String lang)
	{
		Pageable pageable = PageRequest.of(page, size);
		Page<Product> productPage = this.productDAO.getPaginatedByCategory(pageable, categoryId);
		return productPage.map((product) -> this.translationService.translateProduct(product, lang));
	}

	public Page<Product> getPaginatedProducts(int page, int size, String lang)
	{
		Pageable pageable = PageRequest.of(page, size);
		Page<Product> productPage = this.productDAO.getPaginated(pageable);
		return productPage.map((product) -> this.translationService.translateProduct(product, lang));
	}

	public Product getProductBySlug(String slug, String lang)
	{
	    Product product = this.productDAO.getBySlug(slug);
	    return this.translationService.translateProduct(product, lang);
	}

	public void createProduct(ProductDTO productDTO)
	{
		boolean hasEnglishTranslation = false;
	    Category category = this.categoryDAO.get(productDTO.getCategoryId());

	    Product product = new Product()
	        .setPrice(productDTO.getPrice())
	        .setCategory(category)
	        .setQuantity(productDTO.getQuantity())
	        .setAttributes(new ArrayList<>())
	        .setImages(new ArrayList<>())
	        .setTranslations(new ArrayList<>())
	        .setCreatedAt()
	        .setUpdatedAt();

	    for (ProductTranslationDTO translationDTO : productDTO.getTranslations()) {
	        Language language = Language.fromCode(translationDTO.getLanguage());

	        ProductTranslation translation = new ProductTranslation()
	            .setLanguage(language)
	            .setName(translationDTO.getName())
	            .setDescription(translationDTO.getDescription())
	            .setProduct(product);

	        product.getTranslations().add(translation);

	        if (language == Language.EN) {
	            hasEnglishTranslation = true;
	            product.setName(translation.getName())
	                .setDescription(translation.getDescription())
	                .setSlug(SlugUtil.toSlug(translation.getName()));
	        }
	    }

	    if (!hasEnglishTranslation) {
	        throw new IllegalArgumentException("Product must have an English translation.");
	    }

	    if (this.productDAO.getExistsBySlug(product.getSlug()).isPresent()) {
	        throw new IllegalArgumentException("Product slug already exists: " + product.getSlug());
	    }

	    for (ProductAttributeDTO attrDTO : productDTO.getAttributes()) {
	        ProductAttribute attribute = new ProductAttribute()
	            .setName(attrDTO.getName())
	            .setValue(attrDTO.getValue())
	            .setProduct(product);
	        product.getAttributes().add(attribute);
	    }

	    for (String imageName : productDTO.getImages()) {
	        ProductImage image = new ProductImage()
	            .setUrl(imageName)
	            .setProduct(product);
	        product.getImages().add(image);
	    }

	    this.productDAO.create(product);
	}


	public void updateProduct(ProductDTO productDTO, long id)
	{
	    Product product = this.getProductById(id);

		ProductTranslationDTO english = productDTO.getTranslations().stream()
		    .filter(t -> Language.fromCode(t.getLanguage()) == Language.EN)
		    .findFirst()
		    .orElseThrow(() -> new IllegalArgumentException("English translation is required."));

	    product.setName(english.getName())
	           .setDescription(english.getDescription())
	           .setPrice(productDTO.getPrice())
	           .setSlug(SlugUtil.toSlug(english.getName()))
	           .setQuantity(productDTO.getQuantity())
	           .setUpdatedAt();

	    Category category = this.categoryDAO.get(productDTO.getCategoryId());
	    product.setCategory(category);

	    this.updateProductTranslations(product, productDTO.getTranslations());
	    this.updateProductAttributes(product, productDTO.getAttributes());
	    this.updateProductImages(product, productDTO.getImages());

	    this.productDAO.update(product);
	}


	private void updateProductTranslations(Product product, List<ProductTranslationDTO> translationDTOs)
	{
	    List<ProductTranslation> existingTranslations = product.getTranslations();

	    for (ProductTranslationDTO dto : translationDTOs) {
	    	ProductTranslation translation = existingTranslations.stream()
			    .filter(t -> t.getLanguage().getCode().equals(dto.getLanguage()))
			    .findFirst()
			    .orElseGet(() -> {
			        ProductTranslation newTranslation = new ProductTranslation()
			            .setLanguage(Language.fromCode(dto.getLanguage()))
			            .setProduct(product);
			        existingTranslations.add(newTranslation);
			        return newTranslation;
			    });

	        translation.setName(dto.getName());
	        translation.setDescription(dto.getDescription());
	    }

		Set<Language> dtoLanguages = translationDTOs.stream()
		    .map(dto -> Language.fromCode(dto.getLanguage()))
		    .collect(Collectors.toSet());

	    existingTranslations.removeIf(t -> !dtoLanguages.contains(t.getLanguage()));
	}

	private void updateProductAttributes(Product product, List<ProductAttributeDTO> updatedAttributesDTO)
	{
	    List<ProductAttribute> existingAttributes = product.getAttributes();

	    for (ProductAttributeDTO attrDTO : updatedAttributesDTO) {
	        ProductAttribute existingAttr = existingAttributes.stream()
	            .filter(attr -> attr.getName().equals(attrDTO.getName()))
	            .findFirst()
	            .orElse(null);

	        if (existingAttr != null) {
	            existingAttr.setValue(attrDTO.getValue());
	        } else {
	            ProductAttribute newAttr = new ProductAttribute()
	                .setName(attrDTO.getName())
	                .setValue(attrDTO.getValue())
	                .setProduct(product);
	            existingAttributes.add(newAttr);
	        }
	    }

	    existingAttributes.removeIf(attr -> updatedAttributesDTO.stream()
	        .noneMatch(dto -> dto.getName().equals(attr.getName())));
	}

	private void updateProductImages(Product product, List<String> updatedImageNames)
	{
	    List<ProductImage> existingImages = product.getImages();

	    for (String imageName : updatedImageNames) {
	        boolean imageExists = existingImages.stream()
	            .anyMatch((image) -> image.getUrl().equals(imageName));

	        if (!imageExists) {
	            ProductImage newImage = new ProductImage()
	                .setUrl(imageName)
	                .setProduct(product);
	            existingImages.add(newImage);
	        }
	    }

	    existingImages.removeIf((image) -> updatedImageNames.stream()
	        .noneMatch(name -> name.equals(image.getUrl())));
	}

	public void updateActiveProduct(long id)
	{
		Product product = this.productDAO.get(id);
		product.toggleActive();
		this.productDAO.update(product);
	}

	public void deleteProduct(long id)
	{
		Product product = this.productDAO.get(id);
    	List<OrderProduct> orderProducts = this.orderProductDAO.getByProductId(product.getId());
    
        for (OrderProduct orderProduct : orderProducts) {
	        orderProduct.setProduct(null);
	        this.orderProductDAO.update(orderProduct);
	    }

	    this.productDAO.delete(id);
	}
}
