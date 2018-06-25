package com.example.myproject3.service;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.myproject3.model.Delivery;
import com.example.myproject3.model.Order;
import com.example.myproject3.model.WebUser;
import com.example.myproject3.repositories.DeliveryRepository;
import com.example.myproject3.repositories.WebUserRepository;

@RestController
@CrossOrigin(origins = "*")
public class DeliveryService {
	@Autowired
	DeliveryRepository repository;

	@Autowired
	WebUserRepository webUserRepository;
	
//	@GetMapping("/api/delivery/{did}/order")
//	public Iterable<Order> findOrdersByDelivery(@PathVariable("did") int did) {
//		Optional<WebUser> delivery1 = webUserRepository.findById(did);
//		if(delivery1.isPresent()) {
//			Delivery delivery = (Delivery)delivery1.get();
//			return delivery.getOrders();
//		}
//		
//		return null;
//	}
	
	@GetMapping("/api/delivery/orders")
	public Iterable<Order> findOrdersByDelivery(HttpSession session) {
		Delivery currentUser = (Delivery)session.getAttribute("currentUser");
		return currentUser.getOrders();
	}
}
