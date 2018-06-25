package com.example.myproject3.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.myproject3.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{

}
