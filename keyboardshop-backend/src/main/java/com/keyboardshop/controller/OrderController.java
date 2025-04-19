package com.keyboardshop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.keyboardshop.dto.GuestOrderDTO;
import com.keyboardshop.dto.UserOrderDTO;
import com.keyboardshop.models.Order;
import com.keyboardshop.services.OrderService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController
{
	private final OrderService orderService;

	public OrderController(OrderService orderService)
	{
		this.orderService = orderService;
	}

	@GetMapping()
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<List<Order>> getAllOrders()
	{
		return ResponseEntity.ok(this.orderService.getAll());
	}

	@GetMapping("/users/{id}")
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	public ResponseEntity<List<Order>> getAllOrdersFromUserById(@PathVariable long id, @RequestParam(defaultValue = "en") String lang)
	{
		return ResponseEntity.ok(this.orderService.getAllOrdersFromUserById(id, lang));
	}

	@PostMapping
	public ResponseEntity<Map<String, String>> createGuestOrder(@Valid @RequestBody GuestOrderDTO guestOrderDTO)
	{
		this.orderService.createGuestOrder(guestOrderDTO);
		return ResponseEntity.ok(Map.of("message", "Order is created."));
	}

	@PostMapping("/user")
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	public ResponseEntity<Map<String, String>> createUserOrder(@Valid @RequestBody UserOrderDTO userOrderDTO)
	{
		this.orderService.createUserOrder(userOrderDTO);
		return ResponseEntity.ok(Map.of("message", "Order is created."));
	}
}
