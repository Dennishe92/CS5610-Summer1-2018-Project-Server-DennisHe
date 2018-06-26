package com.example.myproject3.service;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myproject3.model.Customer;
import com.example.myproject3.model.Order;
import com.example.myproject3.model.Recipe;
import com.example.myproject3.model.Seller;
import com.example.myproject3.model.WebUser;
import com.example.myproject3.repositories.CustomerRepository;
import com.example.myproject3.repositories.OrderRepository;
import com.example.myproject3.repositories.RecipeRepository;
import com.example.myproject3.repositories.WebUserRepository;

@RestController
@CrossOrigin(origins = "*")
public class CustomerService {
	@Autowired
	CustomerRepository repository;
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@Autowired
	WebUserRepository webUserRepository;
	
	// when customer like recipe, save recipe into like list of customer
//	@PostMapping("/api/customer/{cid}/recipe/{apiId}")
//	public void likeRecipeByCustomer(@PathVariable("apiId") int apiId, @PathVariable("cid") int cid) {
//		Optional<WebUser> customer1 = webUserRepository.findById(cid);
//		if(customer1.isPresent()) {
//			Customer customer = (Customer)customer1.get();
//			Recipe recipe = new Recipe();
//			recipe.setApiId(apiId);
//			recipe.likeCustomer(customer);
//			customer.likeRecipe(recipe);
//			webUserRepository.save(customer);
//			recipeRepository.save(recipe);
//		}
//	}
	
	// when customer like recipe, save recipe into like list of customer
//	@PostMapping("/api/customer/like/recipe/{apiId}")
//	public void likeRecipeByCustomer(@PathVariable("apiId") String apiId, HttpSession session) {
//		System.out.println("session is " + session.getAttribute("currentUser"));
//		Customer customer = (Customer)session.getAttribute("currentUser");
//		System.out.println(customer);
////		if (customer.getLikedRecipes().size() != 0) {
////		for(Recipe recipe : customer.getLikedRecipes()) {
////			if (recipe.getApiId() == apiId) {
////				return;
////			}
////		}
////		}
//		Recipe recipe = new Recipe();
//		recipe.setApiId(apiId);
//		recipe.likeCustomer(customer);
//		customer.likeRecipe(recipe);
//		webUserRepository.save(customer);
//		recipeRepository.save(recipe);
//	}
	
	// customer like seller
//	@PostMapping("/api/customer/{cid}/seller/{sid}")
//	public void followSellerByCustomer(@PathVariable("sid") int sid, @PathVariable("cid") int cid) {
//		Optional<WebUser> customer1 = webUserRepository.findById(cid);
//		Optional<WebUser> seller1 = webUserRepository.findById(sid);
//		
//		if (customer1.isPresent() && seller1.isPresent()) {
//			Customer customer = (Customer)customer1.get();
//			Seller seller = (Seller)seller1.get();
//			customer.followSeller(seller);
//			seller.followCustomer(customer);
//			webUserRepository.save(seller);
//			webUserRepository.save(customer);
//		}
//	}
	
//	@PostMapping("/api/customer/follow/seller/{sid}")
//	public void followSellerByCustomer(@PathVariable("sid") int sid, HttpSession session) {
//		Customer customer = (Customer)session.getAttribute("currentUser");
//		Optional<WebUser> seller1 = webUserRepository.findById(sid);
//		
//		if (seller1.isPresent()) {
//			Seller seller = (Seller)seller1.get();
//			if (customer.getFollowedSellers().contains(seller)) {
//				return;
//			}
//			customer.followSeller(seller);
//			seller.followCustomer(customer);
//			webUserRepository.save(seller);
//			webUserRepository.save(customer);
//		}
//	}
	
	// customer dislike seller
//	@DeleteMapping("/api/customer/{cid}/seller/{sid}")
//	public void disfollowSellerByCustomer(@PathVariable("sid") int sid, @PathVariable("cid") int cid) {
//		Optional<WebUser> customer1 = webUserRepository.findById(cid);
//		Optional<WebUser> seller1 = webUserRepository.findById(sid);
//		
//		if (customer1.isPresent() && seller1.isPresent()) {
//			Customer customer = (Customer)customer1.get();
//			Seller seller = (Seller)seller1.get();
//			customer.disfollowSeller(seller);
//			seller.disfollowCustomer(customer);
//			webUserRepository.save(seller);
//			webUserRepository.save(customer);
//		}
//	}
//	
//	@DeleteMapping("/api/customer/unfollow/seller/{sid}")
//	public void unfollowSellerByCustomer(@PathVariable("sid") int sid, HttpSession session) {
//		Customer customer = (Customer)session.getAttribute("currentUser");
//		Optional<WebUser> seller1 = webUserRepository.findById(sid);
//		if (seller1.isPresent()) {
//			Seller seller = (Seller)seller1.get();
//			customer.disfollowSeller(seller);
//			seller.disfollowCustomer(customer);
//			webUserRepository.save(seller);
//			webUserRepository.save(customer);
//		}
//	}
	
//	@DeleteMapping("/api/customer/{cid}/recipe/{rid}")
//	public void dislikeRecipeByCustomer(@PathVariable("rid") int rid, @PathVariable("cid") int cid) {
//		Optional<WebUser> customer1 = webUserRepository.findById(cid);
//		Optional<Recipe> recipe1 = recipeRepository.findById(rid);
//		
//		if (customer1.isPresent() && recipe1.isPresent()) {
//			Customer customer = (Customer)customer1.get();
//			Recipe recipe = (Recipe)recipe1.get();
//			recipe.dislikeCustomer(customer);
//			customer.dislikeRecipe(recipe);
//			webUserRepository.save(customer);
//			recipeRepository.save(recipe);
//		}
//	}
//	
//	@DeleteMapping("/api/customer/dislike/recipe/{rid}")
//	public void dislikeRecipeByCustomer(@PathVariable("rid") int rid, HttpSession session) {
//		Customer customer = (Customer)session.getAttribute("currentUser");
//		Optional<Recipe> recipe1 = recipeRepository.findById(rid);
//		
//		if (recipe1.isPresent()) {
//			Recipe recipe = (Recipe)recipe1.get();
//			recipe.dislikeCustomer(customer);
//			customer.dislikeRecipe(recipe);
//			webUserRepository.save(customer);
//			recipeRepository.save(recipe);
//		}
//	}

	// Return all recipes the customer like
//	@GetMapping("/api/customer/{cid}/recipe")
//	public Iterable<Recipe> findCustomerLikeRecipes(@PathVariable("cid") int cid){
//		Optional<WebUser> customer1 = webUserRepository.findById(cid);
//		if(customer1.isPresent()) {
//			Customer customer = (Customer)customer1.get();
//			return customer.getLikedRecipes();
//		}
//		
//		return null;
//	}
	
	
//	@GetMapping("/api/customer/recipes")
//	public Iterable<Recipe> findCustomerLikeRecipes(HttpSession session){
//		Customer customer = (Customer)session.getAttribute("currentUser");
//			return customer.getLikedRecipes();
//	}
	
//	@GetMapping("/api/customer/{cid}/order")
//	public Iterable<Order> findOrdersByCustomer(@PathVariable("cid") int cid) {
//		Optional<WebUser> customer1 = webUserRepository.findById(cid);
//		if(customer1.isPresent()) {
//			Customer customer = (Customer)customer1.get();
//			return customer.getOrders();
//		}
//		
//		return null;
//	}
	
//	@GetMapping("/api/customer/orders")
//	public Iterable<Order> findOrdersByCustomer(HttpSession session) {
//		Customer customer = (Customer)session.getAttribute("currentUser");
//			return customer.getOrders();
//	}
	
//	@PostMapping("/api/customer/{cid}/order")
//	public void addOrderByCustomer(@PathVariable("cid") int cid, @RequestBody Order order) {
//		Optional<WebUser> customer1 = webUserRepository.findById(cid);
//		if(customer1.isPresent()) {
//			Customer customer = (Customer)customer1.get();
//			customer.getOrders().add(order);
//			order.setCustomer(customer);
//			order.setCustomerFirstName(customer.getFirstName());
//			order.setCustomerLastName(customer.getLastName());
//		}
//	}
	
//	@PostMapping("/api/customer/order")
//	public void addOrderByCustomer(HttpSession session, @RequestBody Order order) {
//		Customer customer = (Customer)session.getAttribute("currentUser");
//		if (customer.getOrders().contains(order)) {
//			return;
//		}
//			customer.getOrders().add(order);
//			order.setCustomer(customer);
//			order.setCustomerFirstName(customer.getFirstName());
//			order.setCustomerLastName(customer.getLastName());
//	}
	
	@Autowired
	OrderRepository orderRepository;
	
//	@DeleteMapping("api/customer/{cid}/order/{oid}")
//	public void deleteOrderByCustomer(@PathVariable("cid") int cid, @PathVariable int oid) {
//		Optional<WebUser> customer1 = webUserRepository.findById(cid);
//		Optional<Order> order1 = orderRepository.findById(oid);
//		if (customer1.isPresent() && order1.isPresent()) {
//			Customer customer = (Customer)customer1.get();
//			Order order = (Order)order1.get();
//			customer.removeOrder(order);
//			orderRepository.deleteById(oid);
//		}
//	}
//	
//	@DeleteMapping("api/customer/order/{oid}")
//	public void deleteOrderByCustomer(HttpSession session, @PathVariable int oid) {
//		Customer customer = (Customer)session.getAttribute("currentUser");
//		Optional<Order> order1 = orderRepository.findById(oid);
//		if (order1.isPresent()) {
//			Order order = (Order)order1.get();
//			customer.removeOrder(order);
//			orderRepository.deleteById(oid);
//		}
//	}
	
}
