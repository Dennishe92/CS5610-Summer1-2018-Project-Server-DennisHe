package com.example.myproject3.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myproject3.model.Customer;
import com.example.myproject3.model.Order;
//import com.example.myproject3.model.Payment;
import com.example.myproject3.model.Recipe;
import com.example.myproject3.model.Seller;
import com.example.myproject3.model.WebUser;
import com.example.myproject3.repositories.CustomerRepository;
import com.example.myproject3.repositories.OrderRepository;
import com.example.myproject3.repositories.RecipeRepository;
//import com.example.myproject3.repositories.PaymentRepository;
import com.example.myproject3.repositories.WebUserRepository;


@RestController
@CrossOrigin(origins = "*")
public class CustomerService {
	@Autowired
	CustomerRepository repository;

//	@GetMapping("/api/customer")
//	public Iterable<Customer> findAllCustomer() {
//		return repository.findAll();
//	}

//	@PostMapping("/api/customer")
//	public Customer createUser(@RequestBody Customer customer) {
//		return repository.save(customer);
//	}

//	@DeleteMapping("/api/user/{userId}")
//	public void deleteUser(@PathVariable("userId") int id) {
//		repository.deleteById(id);
//	}
	
//	@Autowired
//	PaymentRepository paymentRepository;
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	WebUserRepository webUserRepository;
	
	
	
//	public void likeRecipeInCustomer(@PathVariable("rid") int rid,
//			@PathVariable("cid") int cid) {
//		Optional<Recipe> recipe1 = recipeRepository.findById(rid);
//		Optional<Customer> customer1 = customerRepository.findById(cid);
//		if(recipe1.isPresent() && customer1.isPresent()) {
//			Recipe recipe = recipe1.get();
//			Customer customer = customer1.get();
//			customer.likeRecipe(recipe);
//			customerRepository.save(customer);
//		}
//	}
	
	// when customer like recipe, save recipe into like list of customer
	@PostMapping("/api/customer/{cid}/recipe/{apiId}")
	public void likeRecipeByCustomer(@PathVariable("apiId") int apiId, @PathVariable("cid") int cid) {
		Optional<WebUser> customer1 = webUserRepository.findById(cid);
		if(customer1.isPresent()) {
			Customer customer = (Customer)customer1.get();
			Recipe recipe = new Recipe();
			recipe.setApiId(apiId);
			recipe.likeCustomer(customer);
			customer.likeRecipe(recipe);
			webUserRepository.save(customer);
			recipeRepository.save(recipe);
		}
	}
	
	// customer like seller
	@PostMapping("/api/customer/{cid}/seller/{sid}")
	public void followSellerByCustomer(@PathVariable("sid") int sid, @PathVariable("cid") int cid) {
		Optional<WebUser> customer1 = webUserRepository.findById(cid);
		Optional<WebUser> seller1 = webUserRepository.findById(sid);
		
		if (customer1.isPresent() && seller1.isPresent()) {
			Customer customer = (Customer)customer1.get();
			Seller seller = (Seller)seller1.get();
			customer.followSeller(seller);
			seller.followCustomer(customer);
			webUserRepository.save(seller);
			webUserRepository.save(customer);
		}
	}
	
	// customer dislike seller
	@DeleteMapping("/api/customer/{cid}/seller/{sid}")
	public void disfollowSellerByCustomer(@PathVariable("sid") int sid, @PathVariable("cid") int cid) {
		Optional<WebUser> customer1 = webUserRepository.findById(cid);
		Optional<WebUser> seller1 = webUserRepository.findById(sid);
		
		if (customer1.isPresent() && seller1.isPresent()) {
			Customer customer = (Customer)customer1.get();
			Seller seller = (Seller)seller1.get();
			customer.disfollowSeller(seller);
			seller.disfollowCustomer(customer);
			webUserRepository.save(seller);
			webUserRepository.save(customer);
		}
	}
	
	@DeleteMapping("/api/customer/{cid}/recipe/{rid}")
	public void dislikeRecipeByCustomer(@PathVariable("rid") int rid, @PathVariable("cid") int cid) {
		Optional<WebUser> customer1 = webUserRepository.findById(cid);
		Optional<Recipe> recipe1 = recipeRepository.findById(rid);
		
		if (customer1.isPresent() && recipe1.isPresent()) {
			Customer customer = (Customer)customer1.get();
			Recipe recipe = (Recipe)recipe1.get();
			recipe.dislikeCustomer(customer);
			customer.dislikeRecipe(recipe);
			webUserRepository.save(customer);
			recipeRepository.save(recipe);
		}
	
	}
	
	
	
	// customer dislike seller
	
	
//	// when customer like recipe, save recipe into like list of customer
//	@PostMapping("/api/customer/{cid}/recipe/{rid}")
//	public void likeRecipeByCustomer(@PathVariable("rid") int rid,
//			@PathVariable("cid") int cid) {
//		Optional<Recipe> recipe1 = recipeRepository.findById(rid);
//		Optional<WebUser> customer1 = webUserRepository.findById(cid);
//		if(recipe1.isPresent() && customer1.isPresent()) {
//			Recipe recipe = recipe1.get();
//			Customer customer = (Customer)customer1.get();
//			customer.likeRecipe(recipe);
//			webUserRepository.save(customer);
//		}
//	}

	// Return all recipes the customer like
	@GetMapping("/api/customer/{cid}/recipe")
	public Iterable<Recipe> findCustomerLikeRecipes(@PathVariable("cid") int cid){
		Optional<WebUser> customer1 = webUserRepository.findById(cid);
		if(customer1.isPresent()) {
			Customer customer = (Customer)customer1.get();
			return customer.getLikedRecipes();
		}
		
		return null;
	}
	

	
	@GetMapping("/api/customer/{cid}/order")
	public Iterable<Order> findOrdersByCustomer(@PathVariable("cid") int cid) {
		Optional<WebUser> customer1 = webUserRepository.findById(cid);
		if(customer1.isPresent()) {
			Customer customer = (Customer)customer1.get();
			return customer.getOrders();
		}
		
		return null;
	}
	
	@PostMapping("/api/customer/{cid}/order")
	public void addOrderByCustomer(@PathVariable("cid") int cid, @RequestBody Order order) {
		Optional<WebUser> customer1 = webUserRepository.findById(cid);
		if(customer1.isPresent()) {
			Customer customer = (Customer)customer1.get();
			customer.getOrders().add(order);
			order.setCustomer(customer);
			order.setCustomerFirstName(customer.getFirstName());
			order.setCustomerLastName(customer.getLastName());
		}
	}
	
	@Autowired
	OrderRepository orderRepository;
	
	@DeleteMapping("api/customer/{cid}/order/{oid}")
	public void deleteOrderByCustomer(@PathVariable("cid") int cid, @PathVariable int oid) {
		Optional<WebUser> customer1 = webUserRepository.findById(cid);
		Optional<Order> order1 = orderRepository.findById(oid);
		if (customer1.isPresent() && order1.isPresent()) {
			Customer customer = (Customer)customer1.get();
			Order order = (Order)order1.get();
			customer.removeOrder(order);
			orderRepository.deleteById(oid);
		}
	}
	
	
//	@GetMapping("/api/customer/{cid}/recipe")
//	public Iterable<Recipe> findCustomerLikeRecipes(@PathVariable("cid") int cid){
//		Optional<Customer> customer1 = customerRepository.findById(cid);
//		if(customer1.isPresent()) {
//			Customer customer = customer1.get();
//			return customer.getLikedRecipes();
//		}
//		
//		return null;
//	}
//	
//	@PutMapping("/api/customer/{cid}/owned/{pid}")
//	public void ownedPayment(@PathVariable("pid") int pid, @PathVariable("cid") int cid) {
//		Optional<Customer> customer1 = customerRepository.findById(cid);
//		Optional<Payment> payment1 = paymentRepository.findById(pid);
//		
//		if (customer1.isPresent() && payment1.isPresent()) {
//			Customer customer = customer1.get();
//			Payment payment = payment1.get();
//			payment.setOwner(customer);
//			paymentRepository.save(payment);
//			customer.ownedPayment(payment);
//			customerRepository.save(customer);
//		}
//	}
	
	
}
