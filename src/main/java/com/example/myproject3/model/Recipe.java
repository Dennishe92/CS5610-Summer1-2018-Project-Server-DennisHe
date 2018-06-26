package com.example.myproject3.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Recipe {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String apiId;
	
	@ManyToMany(mappedBy="likedRecipes", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Customer> likedCustomers = new ArrayList<>();
	
	public void likeCustomer(Customer customer) {
		this.likedCustomers.add(customer);
		System.out.println(customer);
		if(!customer.getLikedRecipes().contains(this)) {
			customer.getLikedRecipes().add(this);
		}
	}
	
	public void dislikeCustomer(Customer customer) {
		if(customer.getLikedRecipes().contains(this)) {
			customer.getLikedRecipes().remove(this);
		}
	}
	
	public List<Customer> getLikedCustomers() {
		return this.likedCustomers;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public void setApiId(String apiId) {
		this.apiId = apiId;
	}
	
	public String getApiId() {
		return this.apiId;
	}
	
}
