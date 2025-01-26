package com.shashank.ecom.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shashank.ecom.Services.OrderService;
import com.shashank.ecom.models.Order;

@RestController
public class OrderController {
	
	OrderService OrderService;
	public OrderController(OrderService OrderService) {
		this.OrderService = OrderService;
	}

	@GetMapping("/order/{id}")
	public Order GetSingleOrder(@PathVariable("id") long id) {
		Order SingleOrder;
		SingleOrder = OrderService.GetSingleOrder(id);
		return SingleOrder;  
	}
	
	@GetMapping("/order")
	public List<Order> GetAllOrders(){
		List<Order> allorders = OrderService.GetAllOrders();
		return allorders;
	}

	@PostMapping("/order")
	public Long CreateOrder(@RequestBody Order order) {
		return OrderService.CreateOrder(order.getUser(),order.getProducts());
	}
}
