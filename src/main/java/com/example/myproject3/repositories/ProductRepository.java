package com.example.myproject3.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.myproject3.model.Product;


public interface ProductRepository extends CrudRepository<Product, Integer>{
	@Query("SELECT u FROM Product u WHERE u.name=:name")
	Product findProductByName(
			@Param("name") String name);
}
