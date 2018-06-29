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

	
	@DeleteMapping("/api/recipe/{recipeId}")
	public void deleteRecipe(@PathVariable("recipeId") int id) {
		recipeRepository.deleteById(id);
	}
	
	
	@GetMapping("/api/recipe")
	public Iterable<Recipe> findAllRecipes() {
		return recipeRepository.findAll();
	}
	
}
