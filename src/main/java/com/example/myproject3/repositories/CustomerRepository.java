package com.example.myproject3.repositories;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.myproject3.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>{
}
