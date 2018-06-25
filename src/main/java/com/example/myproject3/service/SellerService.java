package com.example.myproject3.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myproject3.model.Customer;
import com.example.myproject3.model.Order;
import com.example.myproject3.model.Product;
import com.example.myproject3.model.Seller;
import com.example.myproject3.model.WebUser;
import com.example.myproject3.repositories.ProductRepository;
import com.example.myproject3.repositories.SellerRepository;
import com.example.myproject3.repositories.WebUserRepository;

@RestController
@CrossOrigin(origins = "*")
public class SellerService {
	@Autowired
	SellerRepository repository;

//	@GetMapping("/api/seller")
//	public Iterable<Seller> findAllSeller() {
//		return repository.findAll();
//	}
	@Autowired
	WebUserRepository webUserRepository;
	
	@GetMapping("/api/seller/{sid}/product")
	public Iterable<Product> findProductsBySeller(@PathVariable("sid") int sid) {
		Optional<WebUser> seller1 = webUserRepository.findById(sid);
		if(seller1.isPresent()) {
			Seller seller = (Seller)seller1.get();
			return seller.getProducts();
		}
		
		return null;
	}
	
	@Autowired
	ProductRepository productRepository;
	
	@PostMapping("/api/seller/{sid}/product/{pid}")
	public void addProductBySeller(@PathVariable("sid") int sid, @PathVariable("pid") int pid) {
		Optional<WebUser> seller1 = webUserRepository.findById(sid);
		Optional<Product> product1 = productRepository.findById(pid);
		if (seller1.isPresent() && product1.isPresent()) {
			Seller seller = (Seller)seller1.get();
			Product product = (Product)product1.get();
			seller.addProduct(product);
			webUserRepository.save(seller);
		}
	}
	
	@DeleteMapping("/api/seller/{sid}/product/{pid}")
	public void deleteProductBySeller(@PathVariable("sid") int sid, @PathVariable("pid") int pid) {
		Optional<WebUser> seller1 = webUserRepository.findById(sid);
		Optional<Product> product1 = productRepository.findById(pid);
		if (seller1.isPresent() && product1.isPresent()) {
			Seller seller = (Seller)seller1.get();
			Product product = (Product)product1.get();
			seller.deleteProduct(product);
			webUserRepository.save(seller);
		}
	}
}
