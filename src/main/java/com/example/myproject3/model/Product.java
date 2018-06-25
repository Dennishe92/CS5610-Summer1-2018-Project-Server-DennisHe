package com.example.myproject3.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String url;
	
	@ManyToOne()
	@JsonIgnore
	private Seller seller;
	
	public void setSeller(Seller seller) {
		this.seller = seller;
		if(!seller.getProducts().contains(this)) {
			seller.getProducts().add(this);
		}
	}
	
	public Seller getSeller() {
		return this.seller;
	}	
	
	@ManyToOne()
	@JsonIgnore
	private Order order;
	
	public void setOrder(Order order) {
		this.order = order;
		if (!order.getProducts().contains(this)) {
			order.getProducts().add(this);
		}
	}
	
	public Order getOrder() {
		return this.order;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	
}
