package com.example.myproject3.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.myproject3.model.Order;

public interface OrderRepository extends CrudRepository<Order, Integer>{

}
