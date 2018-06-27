package com.example.myproject3.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myproject3.model.Customer;
import com.example.myproject3.model.Order;
import com.example.myproject3.model.Recipe;
import com.example.myproject3.model.WebUser;
import com.example.myproject3.repositories.CustomerRepository;
import com.example.myproject3.repositories.RecipeRepository;
import com.example.myproject3.repositories.WebUserRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class RecipeService {
	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	WebUserRepository webUserRepository;
	
//	@PostMapping("/api/recipe/{rid}/customer/{cid}")
//	public void likeCustomerInRecipe(@PathVariable("rid") int rid,
//			@PathVariable("cid") int cid) {
//		Optional<Recipe> recipe1 = recipeRepository.findById(rid);
//		Optional<Customer> customer1 = customerRepository.findById(cid);
//		if(recipe1.isPresent() && customer1.isPresent()) {
//			Recipe recipe = recipe1.get();
//			Customer customer = customer1.get();
//			recipe.likeCustomer(customer);
//			recipeRepository.save(recipe);
//		}
//	}
	
	// when customer like this recipe, save the customer into like list of the recipe
//	@PostMapping("/api/recipe/{rid}/customer/{cid}")
//	public void likeCustomerInRecipe(@PathVariable("rid") int rid,
//			@PathVariable("cid") int cid) {
//		Optional<Recipe> recipe1 = recipeRepository.findById(rid);
//		Optional<WebUser> customer1 = webUserRepository.findById(cid);
//		if(recipe1.isPresent() && customer1.isPresent()) {
//			Recipe recipe = recipe1.get();
//			Customer customer = (Customer)customer1.get();
//			recipe.likeCustomer(customer);
//			recipeRepository.save(recipe);
//		}
//	}
//	
//	@PostMapping("/api/customer/{cid}/{apiId}")
//	public Recipe saveRecipeByCustomer(@PathVariable("apiId") int apiId, @PathVariable("cid") int cid) {
//		Optional<WebUser> customer1 = webUserRepository.findById(cid);
//		if(customer1.isPresent()) {
//			Customer customer = (Customer)customer1.get();
//			Recipe recipe = new Recipe();
//			recipe.setApiId(apiId);
//			recipe.likeCustomer(customer);
//			recipeRepository.save(recipe);
//		}
//		
//		return null;
//	}
	
	@DeleteMapping("/api/recipe/{recipeId}")
	public void deleteRecipe(@PathVariable("recipeId") int id) {
		recipeRepository.deleteById(id);
	}
	
	
	@GetMapping("/api/recipe")
	public Iterable<Recipe> findAllRecipes() {
		return recipeRepository.findAll();
	}
	
	// Return all customers who like this recipe
//	@GetMapping("/api/recipe/{rid}/customer")
//	public Iterable<Recipe> findCustomerLikeRecipes(@PathVariable("cid") int cid){
//		Optional<WebUser> customer1 = webUserRepository.findById(cid);
//		if(customer1.isPresent()) {
//			Customer customer = (Customer)customer1.get();
//			return customer.getLikedRecipes();
//		}
//		
//		return null;
//	}
}
