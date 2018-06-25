//package com.example.myproject3.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.myproject3.model.Payment;
//import com.example.myproject3.repositories.PaymentRepository;
//
//@RestController
//public class PaymentService {
//	@Autowired
//	PaymentRepository paymentRepository;
//	
//	@PostMapping("api/payment")
//	public Payment createPayment(@RequestBody Payment payment) {
//		return paymentRepository.save(payment);
//	}
//}
