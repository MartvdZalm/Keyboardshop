// package com.keyboardshop.backend.dao;

// import com.keyboardshop.backend.repository.OrderRepository;
// import com.keyboardshop.backend.models.CustomUser;
// import com.keyboardshop.backend.models.Order;
// import com.keyboardshop.backend.models.Product;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import java.util.List;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// @ExtendWith(MockitoExtension.class)
// public class OrderDAOTest
// {
// 	@Mock
// 	private OrderRepository orderRepository;

//     @InjectMocks
//     private OrderDAO orderDAO;

// 	@Test
// 	public void testGetAllOrdersFromUserById()
// 	{
// 		long id = 1L;
// 		Order order1 = new Order();
// 		order1.setUser(new CustomUser());
// 		order1.setProducts(List.of(
// 			new Product().setName("Product 1"),
// 			new Product().setName("Product 2"),
// 			new Product().setName("Product 3")
// 		));

// 		List<Order> mockOrders = List.of(order1);

// 		when(this.orderRepository.findByUserId(id)).thenReturn(mockOrders);

// 		List<Order> orders = this.orderDAO.getAllOrdersFromUserById(id);
// 		assertNotNull(orders);
// 		assertEquals(1, orders.size());
// 		assertEquals(3, orders.get(0).getProducts().size());
// 		verify(this.orderRepository, times(1)).findByUserId(id);
// 	}
// }
