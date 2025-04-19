package com.keyboardshop.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.keyboardshop.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

@Entity
@Table(name = "\"order\"")
public class Order
{
	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = true)
	private CustomUser user;
	
    private String guestEmail;
    private String guestFirstName;
    private String guestLastName;

    private String streetName;
    private String houseNumber;
    private String postalCode;
    private String city;

    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status = OrderStatus.PENDING;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderProduct> products = new ArrayList<>();

	public Order() {}

	public long getId()
	{
		return id;
	}

	public Order setId(long id)
	{
		this.id = id;
		return this;
	}

    public CustomUser getUser()
    {
        return user;
    }

    public Order setUser(CustomUser user)
    {
        this.user = user;
        return this;
    }

    public String getGuestEmail()
    {
        return guestEmail;
    }

    public Order setGuestEmail(String guestEmail)
    {
        this.guestEmail = guestEmail;
        return this;
    }

    public String getGuestFirstName()
    {
    	return guestFirstName;
    }

    public Order setGuestFirstName(String guestFirstName)
    {
    	this.guestFirstName = guestFirstName;
    	return this;
    }

    public String getGuestLastName()
    {
    	return guestLastName;
    }

    public Order setGuestLastName(String guestLastName)
    {
    	this.guestLastName = guestLastName;
    	return this;
    }

    public String getStreetName()
    {
    	return streetName;
    }

    public Order setStreetName(String streetName)
    {
    	this.streetName = streetName;
    	return this;
    }

    public String getHouseNumber()
    {
    	return houseNumber;
    }

    public Order setHouseNumber(String houseNumber)
    {
    	this.houseNumber = houseNumber;
    	return this;
    }

    public String getPostalCode()
    {
    	return postalCode;
    }

    public Order setPostalCode(String postalCode)
    {
    	this.postalCode = postalCode;
    	return this;
    }

    public String getCity()
    {
    	return city;
    }

    public Order setCity(String city)
    {
    	this.city = city;
    	return this;
    }

    public BigDecimal getTotal()
    {
        return total;
    }

    public Order setTotal(BigDecimal total)
    {
        this.total = total;
        return this;
    }

    public OrderStatus getStatus()
    {
        return status;
    }

    public Order setStatus(OrderStatus status)
    {
        this.status = status;
        return this;
    }

    public LocalDateTime getCreatedAt()
    {
        return createdAt;
    }

    public Order setCreatedAt()
    {
        this.createdAt = LocalDateTime.now();
        return this;
    }

    public void addProduct(OrderProduct orderProduct)
    {
        this.products.add(orderProduct);
    }

    public List<OrderProduct> getProducts()
    {
        return products;
    }

    public Order setProducts(List<OrderProduct> products)
    {
        this.products = products;
        return this;
    }

}
