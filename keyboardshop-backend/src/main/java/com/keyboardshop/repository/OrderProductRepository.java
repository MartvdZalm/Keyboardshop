package com.keyboardshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keyboardshop.models.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long>
{
	public List<OrderProduct> findByProductId(long id);
}
