package com.example.myproject3.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Delivery extends WebUser {
	@OneToMany(mappedBy="delivery")
	@JsonIgnore
	private List<Order> orders;
	
	public void deliveryOrder(Order order) {
		this.orders.add(order);
		if(order.getDelivery() != this) {
			order.setDelivery(this);
		}
	}
	
	public List<Order> getOrders() {
		return orders;
	}
}