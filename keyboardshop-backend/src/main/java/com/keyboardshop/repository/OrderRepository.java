package com.keyboardshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keyboardshop.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long>
{
	public List<Order> findByUserId(long id);
}
