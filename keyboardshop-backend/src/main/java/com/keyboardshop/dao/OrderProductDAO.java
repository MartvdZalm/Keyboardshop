package com.keyboardshop.dao;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.keyboardshop.models.OrderProduct;
import com.keyboardshop.repository.OrderProductRepository;

@Component
public class OrderProductDAO implements DAO<OrderProduct>
{
	private final OrderProductRepository orderProductRepository;

	public OrderProductDAO(OrderProductRepository orderProductRepository)
	{
		this.orderProductRepository = orderProductRepository;
	}

	public OrderProduct get(long id)
	{
		return this.orderProductRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(
				HttpStatus.NOT_FOUND,
				"Order product with the id " + id + " not found."
			));
	}

	public List<OrderProduct> getAll()
	{
		return this.orderProductRepository.findAll();
	}

	public List<OrderProduct> getByProductId(long id)
	{
		return this.orderProductRepository.findByProductId(id);
	}

	public void create(OrderProduct orderProduct)
	{
		this.orderProductRepository.save(orderProduct);
	}

	public void update(OrderProduct orderProduct)
	{
		this.orderProductRepository.save(this.get(orderProduct.getId()));
	}

	public void delete(long id)
	{
		this.orderProductRepository.deleteById(id);
	}
}
