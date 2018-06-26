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
	

}
