package com.example.myproject3.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String address;
	private String customerFirstName;
	private String customerLastName;
	
	public String getCustomerFirstName() {
		return this.customerFirstName;
	}
	
	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName =customerFirstName;
	}
	
	public String getCustomerLastName() {
		return this.customerLastName;
	}
	
	public void setCustomerLastName(String customerLastName) {
		this.customerLastName =customerLastName;
	}
	
	@ManyToOne
	@JsonIgnore
	private Customer customer;
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
		this.customerFirstName = customer.getFirstName();
		this.customerLastName = customer.getLastName();
		
		if(!customer.getOrders().contains(this)) {
			customer.getOrders().add(this);
		}
	}
	
	@OneToMany(mappedBy="order")
	@JsonIgnore
	private List<Product> products;
	
	public void addProduct(Product product) {
		this.products.add(product);
		if (product.getOrder() != this) {
			product.setOrder(this);
		}
	}
	
	public void deleteProduct(Product product) {
		if (products.contains(product)) {
			products.remove(product);
		}
	}
	
	public List<Product> getProducts() {
		return this.products;
	}
	
	
	public Customer getCustomer() {
		return this.customer;
	}	
	
	@ManyToOne()
	@JsonIgnore
	private Delivery delivery;
	
	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
		if(!delivery.getOrders().contains(this)) {
			delivery.getOrders().add(this);
		}
	}
	
	public Delivery getDelivery() {
		return this.delivery;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return this.address;
	}	

}
