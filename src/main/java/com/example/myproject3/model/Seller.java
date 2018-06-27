package com.example.myproject3.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Seller extends WebUser {
	@OneToMany(mappedBy="seller")
	@JsonIgnore
	private List<Product> products;
	
	public void addProduct(Product product) {
		this.products.add(product);
		if(product.getSeller() != this) {
			product.setSeller(this);
		}
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
	public void deleteProduct(Product product) {
		if (this.products.contains(product)) {
			this.products.remove(product);
		}
	}
	
	@ManyToMany(mappedBy="followedSellers")
	@JsonIgnore
	private List<Customer> followedCustomers = new ArrayList<>();
	
	public void followCustomer(Customer customer) {
		this.followedCustomers.add(customer);
		if(!customer.getFollowedSellers().contains(this)) {
			customer.getFollowedSellers().add(this);
		}
	}
	
	public List<Customer> getFollowedCustomers() {
		return this.followedCustomers;
	}
	
	public void disfollowCustomer(Customer customer) {
		if (this.followedCustomers.contains(customer)) {
			this.followedCustomers.remove(customer);
		}
	}

}
