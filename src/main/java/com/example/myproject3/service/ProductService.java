package com.example.myproject3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myproject3.model.Product;
import com.example.myproject3.repositories.ProductRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;

	// add product
	@PostMapping("/api/product")
	public Product createProduct(@RequestBody Product product) {
		return productRepository.save(product);
	}
	
	@DeleteMapping("/api/product/{pid}")
	public void deleteProduct(@PathVariable("pid") int pid) {
		productRepository.deleteById(pid);
	}
}
