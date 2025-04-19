package com.keyboardshop.dao;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.keyboardshop.models.Order;
import com.keyboardshop.repository.OrderRepository;

@Component
public class OrderDAO implements DAO<Order>
{
	private final OrderRepository orderRepository;

	public OrderDAO(OrderRepository orderRepository)
	{
		this.orderRepository = orderRepository;
	}

	public Order get(long id)
	{
		return this.orderRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(
				HttpStatus.NOT_FOUND,
				"Order with the id " + id + " not found."
			));
	}

	public List<Order> getAll()
	{
		return this.orderRepository.findAll();
	}

	public List<Order> getByUserId(long id)
	{
		return this.orderRepository.findByUserId(id);
	}

	public void create(Order order)
	{
		this.orderRepository.save(order);
	}

	public void update(Order order)
	{
		this.orderRepository.save(this.get(order.getId()));
	}

	public void delete(long id)
	{
		this.orderRepository.deleteById(id);
	}
}
