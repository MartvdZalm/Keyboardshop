// package com.keyboardshop.backend.dao;

// import com.keyboardshop.backend.repository.CategoryRepository;
// import com.keyboardshop.backend.dto.CategoryDTO;
// import com.keyboardshop.backend.models.Category;
// import com.keyboardshop.backend.services.ValidationService;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.http.HttpStatus;
// import org.springframework.web.server.ResponseStatusException;

// import java.util.List;
// import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// @ExtendWith(MockitoExtension.class)
// public class CategoryDAOTest
// {
// 	@Mock
// 	private CategoryRepository categoryRepository;

// 	@Mock
// 	private ValidationService validationService;

//     @InjectMocks
//     private CategoryDAO categoryDAO;

// 	@Test
// 	public void testGetAllCategories()
// 	{
// 	    List<Category> mockCategories = List.of(
// 	        new Category("Category 1"),
// 	        new Category("Category 2")
// 	    );
// 	    when(this.categoryRepository.findAll()).thenReturn(mockCategories);

// 	    List<Category> categories = this.categoryDAO.getAllCategories();
// 	    assertNotNull(categories);
// 	    assertEquals(2, categories.size());
// 	    assertEquals("Category 1", categories.get(0).getName());
// 	    assertEquals("Category 2", categories.get(1).getName());
// 	    verify(categoryRepository, times(1)).findAll();
// 	}

// 	@Test
// 	public void testCreateCategory()
// 	{
// 	    CategoryDTO categoryDTO = new CategoryDTO("Keyboards");
// 	    doNothing().when(validationService).validateModel(any(Category.class));
// 	    categoryDAO.createCategory(categoryDTO);
// 	    verify(this.categoryRepository, times(1)).save(any(Category.class));
// 	}

// 	@Test
// 	public void testUpdateCategoryById()
// 	{
// 		long id = 1L;
// 		CategoryDTO categoryDTO = new CategoryDTO("Updated Keyboards");
// 		Category existingCategory = new Category("Keyboards");

// 		when(this.categoryRepository.findById(id)).thenReturn(Optional.of(existingCategory));
// 		doNothing().when(this.validationService).validateModel(existingCategory);

// 		categoryDAO.updateCategoryById(id, categoryDTO);

// 		assertEquals("Updated Keyboards", existingCategory.getName());
// 		verify(this.categoryRepository, times(1)).save(existingCategory);
// 	}

// 	@Test
// 	public void testUpdateCategoryByIdNotFound()
// 	{
// 		long id = 1L;
// 		CategoryDTO categoryDTO = new CategoryDTO("Updated Keyboards");
// 		when(this.categoryRepository.findById(id)).thenReturn(Optional.empty());

// 		ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
// 			categoryDAO.updateCategoryById(id, categoryDTO);
// 		});

// 		assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
// 		assertTrue(exception.getReason().contains("Category with the id " + id + " not found"));
// 		verify(this.categoryRepository, never()).save(any());
// 	}
// }
