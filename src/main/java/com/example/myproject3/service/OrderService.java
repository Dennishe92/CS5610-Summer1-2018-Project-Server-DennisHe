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

import com.example.myproject3.model.Order;
import com.example.myproject3.model.Product;
import com.example.myproject3.repositories.OrderRepository;
import com.example.myproject3.repositories.ProductRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	// find all orders
	@GetMapping("/api/order")
	public Iterable<Order> findAllOrders() {
		return orderRepository.findAll();
	}
	
	
	@PostMapping("/api/order")
	public Order createOrder(@RequestBody Order order) {
		return orderRepository.save(order);
	}
	
	@DeleteMapping("/api/order/{orderId}")
	public void deleteOrder(@PathVariable("orderId") int id) {
		orderRepository.deleteById(id);
	}
	
	@DeleteMapping("/api/order/{oid}/product/{pid}")
	public void deleteProductInOrder(@PathVariable("oid") int oid, @PathVariable("pid") int pid) {
		Optional<Order> order1 = orderRepository.findById(oid);
		Optional<Product> product1 = productRepository.findById(pid);
		
		if (order1.isPresent() && product1.isPresent()) {
			Order order = (Order)order1.get();
			Product product = (Product)product1.get();
			order.deleteProduct(product);
			orderRepository.save(order);
		}
	}
	
	@PostMapping("/api/order/{oid}/product/{pid}")
	public void AddProductInOrder(@PathVariable("oid") int oid, @PathVariable("pid") int pid) {
		Optional<Order> order1 = orderRepository.findById(oid);
		Optional<Product> product1 = productRepository.findById(pid);
		if (order1.isPresent() && product1.isPresent()) {
			Order order = (Order)order1.get();
			Product product = (Product)product1.get();
			order.addProduct(product);
			product.setOrder(order);
		}
	}
	
	
		
	
	
}
