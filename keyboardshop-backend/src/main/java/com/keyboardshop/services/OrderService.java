package com.keyboardshop.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.keyboardshop.dao.CustomUserDAO;
import com.keyboardshop.dao.OrderDAO;
import com.keyboardshop.dao.ProductDAO;
import com.keyboardshop.dto.BaseOrderDTO;
import com.keyboardshop.dto.GuestOrderDTO;
import com.keyboardshop.dto.OrderProductDTO;
import com.keyboardshop.dto.UserOrderDTO;
import com.keyboardshop.models.CustomUser;
import com.keyboardshop.models.Order;
import com.keyboardshop.models.OrderProduct;
import com.keyboardshop.models.Product;

@Service
public class OrderService
{
	private final OrderDAO orderDAO;
	private final CustomUserDAO customUserDAO;
	private final ProductDAO productDAO;
	private final TranslationService translationService;

	public OrderService(OrderDAO orderDAO, CustomUserDAO customUserDAO, ProductDAO productDAO, TranslationService translationService)
	{
		this.orderDAO = orderDAO;
		this.customUserDAO = customUserDAO;
		this.productDAO = productDAO;
		this.translationService = translationService;
	}

	public List<Order> getAll()
	{
		return this.orderDAO.getAll();
	}

	public List<Order> getAllOrdersFromUserById(long id, String lang)
	{
		CustomUser user = this.customUserDAO.get(id);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        CustomUser customUser = this.customUserDAO.getByEmail(email);

        if (user.getId() != customUser.getId() && customUser.getRole().equals("USER")) {
			throw new ResponseStatusException(
				HttpStatus.FORBIDDEN, "Can't get orders from other users."
			);
        }

		return this.orderDAO.getByUserId(id)
	        .stream()
	        .peek((order) -> order.setProducts(
	            order.getProducts()
	                .stream()
	                .peek((product) -> product.setProduct(
	                    this.translationService.translateProduct(product.getProduct(), lang)
	                ))
	                .collect(Collectors.toList()) 
	        ))
	        .collect(Collectors.toList());
	}

	public Order getById(long id)
	{
		return this.orderDAO.get(id);
	}

	public void createGuestOrder(GuestOrderDTO orderDTO)
	{
	    Order order = new Order()
	        .setGuestEmail(orderDTO.getEmail())
	        .setGuestFirstName(orderDTO.getFirstName())
	        .setGuestLastName(orderDTO.getLastName());

	    this.buildOrderBase(orderDTO, order);
	    this.orderDAO.create(order);
	}

	public void createUserOrder(UserOrderDTO orderDTO)
	{
	    CustomUser user = this.customUserDAO.get(orderDTO.getUserId());

	    Order order = new Order()
	        .setUser(user);

	    this.buildOrderBase(orderDTO, order);
	    this.orderDAO.create(order);
	}

	private Order buildOrderBase(BaseOrderDTO orderDTO, Order order)
	{
	    BigDecimal total = BigDecimal.ZERO;

	    order.setCreatedAt();
	    
	    for (OrderProductDTO productDTO : orderDTO.getProducts()) {
	        Product product = this.productDAO.get(productDTO.getProductId());
	        product.setQuantity(product.getQuantity() - productDTO.getQuantity());

	        OrderProduct orderProduct = new OrderProduct()
	            .setProduct(product)
	            .setOrder(order)
	            .setProductName(product.getName())
	            .setProductDescription(product.getDescription())
	            .setProductPrice(product.getPrice())
	            .setQuantity(productDTO.getQuantity());

	        if (!product.getImages().isEmpty()) {
	            orderProduct.setProductImage(product.getImages().get(0).getUrl());
	        }

	        order.addProduct(orderProduct);

	        total = total.add(product.getPrice().multiply(BigDecimal.valueOf(productDTO.getQuantity())));

	        this.productDAO.update(product);
	    }

	    return order
	        .setStreetName(orderDTO.getStreetName())
	        .setHouseNumber(orderDTO.getHouseNumber())
	        .setPostalCode(orderDTO.getPostalCode())
	        .setCity(orderDTO.getCity())
	        .setTotal(total);
	}
}
