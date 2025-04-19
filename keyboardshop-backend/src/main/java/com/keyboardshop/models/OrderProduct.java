package com.keyboardshop.models;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "order_products")
public class OrderProduct
{
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    @ManyToOne
    @JoinColumn(nullable = true)
    @JsonManagedReference
    private Product product;

    private String productName;
    private String productImage;
    @Column(columnDefinition = "TEXT")
    private String productDescription;
    @Column(nullable = false)
    private BigDecimal productPrice;

    @Column(nullable = false)
    private int quantity;

    public OrderProduct() {}

    public Long getId()
    {
        return id;
    }

    public OrderProduct setId(Long id)
    {
        this.id = id;
        return this;
    }

    public Order getOrder()
    {
        return order;
    }

    public OrderProduct setOrder(Order order)
    {
        this.order = order;
        return this;
    }

    public Product getProduct()
    {
        return product;
    }

    public OrderProduct setProduct(Product product)
    {
        this.product = product;
        return this;
    }

    public String getProductImage()
    {
        return productImage;
    }

    public OrderProduct setProductImage(String productImage)
    {
        this.productImage = productImage;
        return this; 
    }

    public String getProductName()
    {
        return productName;
    }

    public OrderProduct setProductName(String productName)
    {
        this.productName = productName;
        return this;
    }

    public String getProductDescription()
    {
        return productDescription;
    }

    public OrderProduct setProductDescription(String productDescription)
    {
        this.productDescription = productDescription;
        return this;
    }

    public BigDecimal getProductPrice()
    {
        return productPrice;
    }

    public OrderProduct setProductPrice(BigDecimal productPrice)
    {
        this.productPrice = productPrice;
        return this;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public OrderProduct setQuantity(int quantity)
    {
        this.quantity = quantity;
        return this;
    }
}
