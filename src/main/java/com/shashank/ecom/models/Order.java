package com.shashank.ecom.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="\"Order\"")
public class Order extends BaseModel {
	@ManyToOne
	private User user;
	
	private Double price;
	private OrderStatus orderstatus;
	
	@ManyToMany
	private List<Product> products;

	public Order(User user, Double price,
			OrderStatus orderstatus, List<Product> products) {
		this.user = user;
		this.price = price;
		this.orderstatus = orderstatus;
		this.products = products;
	}

	public Order() {
	
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public OrderStatus getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(OrderStatus orderstatus) {
		this.orderstatus = orderstatus;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
