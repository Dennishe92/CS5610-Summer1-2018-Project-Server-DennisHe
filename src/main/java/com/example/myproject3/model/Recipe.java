package com.example.myproject3.model;

import java.util.List;

import javax.persistence.Entity;
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
//	private String name;
//	private int rating;
//	private String url;
	 private int apiId;
	
	@ManyToMany(mappedBy="likedRecipes")
	@JsonIgnore
	private List<Customer> likedCustomers;
	
//	@ManyToMany(mappedBy="productRecipes")
//	@JsonIgnore
//	private List<Product> recipeProduct;
	
	public void likeCustomer(Customer customer) {
		this.likedCustomers.add(customer);
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
	
//	public List<Product> getRecipeProduct() {
//		return this.recipeProduct;
//	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public void setApiId(int apiId) {
		this.apiId = apiId;
	}
	
	public int getApiId() {
		return this.apiId;
	}
	
//	
//	public void setUrl(String url) {
//		this.url = url;
//	}
//
//	public String getUrl() {
//		return url;
//	}
//	
//	public void setName(String name) {
//		this.name = name;
//	}
////	
//	public String getName() {
//		return this.name;
//	}
//
//	public void setRating(int rating) {
//		this.rating = rating;
//	}
//	
//	public int getRating() {
//		return this.rating;
//	}	
	
}
