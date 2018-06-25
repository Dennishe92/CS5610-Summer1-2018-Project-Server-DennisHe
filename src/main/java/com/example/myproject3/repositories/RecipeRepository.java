package com.example.myproject3.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.myproject3.model.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Integer> {

}
