package com.example.myproject3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
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
import com.example.myproject3.model.Delivery;
import com.example.myproject3.model.Order;
import com.example.myproject3.model.Product;
import com.example.myproject3.model.Recipe;
import com.example.myproject3.model.Seller;
import com.example.myproject3.model.WebUser;
import com.example.myproject3.repositories.OrderRepository;
import com.example.myproject3.repositories.ProductRepository;
import com.example.myproject3.repositories.RecipeRepository;
import com.example.myproject3.repositories.WebUserRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WebUserService {
	private HttpSession session;

	@Autowired
	WebUserRepository repository;
	
	@PostMapping("/api/user/Customer")
	public Customer createCustomer(@RequestBody Customer customer, HttpServletResponse response) {
		String username = customer.getUsername();
		customer.setRole("Customer");
		if (repository.findUserByUsername(username) != null) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			return null;
		}
		return repository.save(customer);
	}
	
	@PostMapping("/api/user/Seller")
	public Seller createSeller(@RequestBody Seller seller, HttpServletResponse response) {
		String username = seller.getUsername();
		seller.setRole("Seller");
		if (repository.findUserByUsername(username) != null) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			return null;
		}
		
		return repository.save(seller);
	}
	
	@PostMapping("/api/user/Delivery")
	public Delivery createDelivery(@RequestBody Delivery delivery, HttpServletResponse response) {
		String username = delivery.getUsername();
		delivery.setRole("Delivery");
		if (repository.findUserByUsername(username) != null) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			return null;
		}
		
		return repository.save(delivery);
	}
	
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		repository.deleteById(id);
	}
	
//	@PostMapping("/api/login")
//	public WebUser login(@RequestBody WebUser user, HttpServletRequest request, HttpServletResponse response) {
//		WebUser res = repository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
//		if (res != null) {
//			request.getServletContext().setAttribute("currentUser", res);
//			return res;
//		} 
//		response.setStatus(HttpServletResponse.SC_CONFLICT);
//		return null;
//	}
	
	@PostMapping("/api/login")
	public WebUser login(@RequestBody WebUser user, HttpServletResponse response, HttpServletRequest request) {
		WebUser res = repository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
		session = request.getSession();
		if (res != null) {
			session.setAttribute("currentUser", res);
			System.out.println("login success!" + session.getAttribute("currentUser"));
			return res;
		} 
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		return null;
	}
	
	@GetMapping("/api/users")
	public Iterable<WebUser> findAllUsers() {
		return repository.findAll();
	}
	
	@PostMapping("/api/register")
	public WebUser register(@RequestBody WebUser user, HttpServletResponse response) {
		WebUser data = repository.findUserByUsername(user.getUsername());
		if (data == null) {
			WebUser res = repository.save(user);
			return res;	
		} else {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			return null;
		}
	}
	
	@GetMapping("/api/profile")
	public WebUser populateProfile(HttpServletResponse response, HttpServletRequest request) {
		WebUser currentUser = (WebUser)session.getAttribute("currentUser");
		
		if (currentUser == null) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			return null;
		}
		return currentUser;
	}
	
	@PutMapping("/api/profile/{uid}")
	public WebUser updateProfile(@RequestBody WebUser user, @PathVariable("uid") int uid, HttpServletResponse response) {
		Optional<WebUser> current = repository.findById(uid);
		
		if (current.isPresent()) {
			Customer cur = (Customer)(current.get());
			cur.setUsername(user.getUsername());
			cur.setPhone(user.getPhone());
			cur.setEmail(user.getEmail());
			repository.save(cur);
			return cur; 
		}
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		return null;
	}
		
//	@PostMapping("/api/profile")
//	public void logout(HttpServletRequest request) {
//		request.getServletContext().removeAttribute("currentUser");
//	}	
	
	@PostMapping("/api/logout")
	public void logout(HttpServletResponse response) {
//		this.session.invalidate();
		session.removeAttribute("currentUser");
		response.setStatus(HttpServletResponse.SC_CONFLICT);
	}	
	
	@Autowired
	RecipeRepository recipeRepository;
	
	@PostMapping("/api/customer/like/recipe/{apiId}")
	public void likeRecipeByCustomer(@PathVariable("apiId") String apiId, HttpServletRequest request) {
		Customer customer = (Customer)session.getAttribute("currentUser");
		System.out.println("hello");
		System.out.println(customer);
		if (customer.getLikedRecipes().size() != 0) {
		for(Recipe recipe : customer.getLikedRecipes()) {
			if (recipe.getApiId().equals(apiId)) {
				return;
			}
		}
		}
		Recipe recipe = new Recipe();
		recipe.setApiId(apiId);
		recipeRepository.save(recipe);
		recipe.likeCustomer(customer);
//		customer.likeRecipe(recipe);
		repository.save(customer);	
	}

	@PostMapping("/api/customer/follow/seller/{sid}")
	public void followSellerByCustomer(@PathVariable("sid") int sid) {
		Customer customer = (Customer)session.getAttribute("currentUser");
		Optional<WebUser> seller1 = repository.findById(sid);
		System.out.println(customer);
		if (seller1.isPresent()) {
			Seller seller = (Seller)seller1.get();
			if (customer.getFollowedSellers().contains(seller)) {
				return;
			}
			customer.followSeller(seller);
			seller.followCustomer(customer);
			repository.save(seller);
			repository.save(customer);
		}
	}
	
	
//	@DeleteMapping("/api/customer/unfollow/seller/{sid}")
//	public void unfollowSellerByCustomer(@PathVariable("sid") int sid) {
//		Customer customer = (Customer)session.getAttribute("currentUser");
//		Optional<WebUser> seller1 = repository.findById(sid);
//		System.out.println(customer);
//		if (seller1.isPresent()) {
//			Seller seller = (Seller)seller1.get();
//			customer.disfollowSeller(seller);
//			seller.disfollowCustomer(customer);
//			repository.save(seller);
//			repository.save(customer);
//		}
//	}
//	
	
	@DeleteMapping("/api/customer/{cid}/seller/{sid}")
	public void unfollowSellerByCustomer(@PathVariable("sid") int sid, @PathVariable("cid") int cid) {
		Optional<WebUser> cus1 = repository.findById(cid);
		Optional<WebUser> sel1 = repository.findById(sid);
		
		if (cus1.isPresent() && sel1.isPresent()) {
			Customer customer = (Customer)(cus1.get());
			Seller seller = (Seller)(sel1.get());
			customer.disfollowSeller(seller);
			seller.disfollowCustomer(customer);
			repository.save(customer);
			repository.save(seller);
		}
	}
// fixme : use user id or not?
//	@DeleteMapping("/api/customer/dislike/recipe/{rid}")
//	public void dislikeRecipeByCustomer(@PathVariable("rid") int rid) {
//		Customer customer = (Customer)session.getAttribute("currentUser");
//		Optional<Recipe> recipe1 = recipeRepository.findById(rid);
	
//		if (recipe1.isPresent()) {
//			Recipe recipe = (Recipe)recipe1.get();
//			recipe.dislikeCustomer(customer);
//			customer.dislikeRecipe(recipe);
//			repository.save(customer);
//			recipeRepository.save(recipe);
//		}
//	}	
	
//	@GetMapping("/api/customer/recipes")
//	public Iterable<Recipe> findCustomerLikeRecipes(){
//		Customer customer = (Customer)session.getAttribute("currentUser");
//			return customer.getLikedRecipes();
//	}
	
	@GetMapping("/api/customer/{uid}/recipe")
	public Iterable<Recipe> findCustomerLikeRecipes(@PathVariable("uid") int uid){
		Optional<WebUser> data = repository.findById(uid);
		if (data.isPresent()) {
			System.out.println(data);
		Customer customer = (Customer)(data.get());
		return customer.getLikedRecipes();
		}
		return null;
	}
	
	@GetMapping("/api/customer/{uid}/order")
	public Iterable<Order> findOrdersByCustomer(@PathVariable("uid") int uid) {
		Optional<WebUser> data = repository.findById(uid);
		if (data.isPresent()) {
			Customer customer = (Customer)(data.get());
			return customer.getOrders();
		}
		
		return null;
	}
	
	@PostMapping("/api/customer/{uid}/order")
	public void addOrderByCustomer(@PathVariable("uid") int uid, @RequestBody Order order) {
		Optional<WebUser> data = repository.findById(uid);
		if (data.isPresent()) {
			Customer customer = (Customer)(data.get());
			if (customer.getOrders().contains(order)) {
				return;
			}
			customer.getOrders().add(order);
			order.setCustomer(customer);
			order.setCustomerFirstName(customer.getFirstName());
			order.setCustomerLastName(customer.getLastName());
		}
	}

	@Autowired
	OrderRepository orderRepository;

	@DeleteMapping("api/customer/{uid}/order/{oid}")
	public void deleteOrderByCustomer(@PathVariable int oid, @PathVariable int uid) {
		Optional<WebUser> data = repository.findById(uid);
		Optional<Order> order1 = orderRepository.findById(oid);
		if (data.isPresent() && order1.isPresent()) {
			Customer customer = (Customer)(data.get());
			Order order = (Order)(order1.get());
			customer.removeOrder(order);
			orderRepository.deleteById(oid);
		}
	}
	
	@DeleteMapping("/api/customer/{uid}/recipe/{rid}")
	public void dislikeRecipeByCustomer(@PathVariable("rid") int rid, @PathVariable("uid") int uid) {
		Optional<WebUser> data = repository.findById(uid);
		Optional<Recipe> recipe1 = recipeRepository.findById(rid);
		
		if (recipe1.isPresent() && data.isPresent()) {
			Customer customer = (Customer)(data.get());
			Recipe recipe = (Recipe)recipe1.get();
			recipe.dislikeCustomer(customer);
			customer.dislikeRecipe(recipe);
			repository.save(customer);
			recipeRepository.save(recipe);
		}
	}

	@GetMapping("/api/delivery/{uid}/order")
	public Iterable<Order> findOrdersByDelivery(@PathVariable int uid, HttpServletResponse response) {
		Optional<WebUser> data = repository.findById(uid);
		if (data.isPresent()) {
			Delivery delivery = (Delivery)(data.get());
			return delivery.getOrders();
		}
		
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		return null;
	}

	@GetMapping("/api/seller/{uid}/product")
	public Iterable<Product> findProductsBySeller(@PathVariable int uid, HttpServletResponse response) {
		Optional<WebUser> data = repository.findById(uid);
		if (data.isPresent()) {
			Seller seller = (Seller)(data.get());
			return seller.getProducts();
		}
		
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		return null;
	}
	
	@Autowired
	ProductRepository productRepository;

	@PostMapping("/api/seller/{uid}/product")
	public void addProductBySeller(@RequestBody Product product, @PathVariable int uid) {
		Optional<WebUser> data = repository.findById(uid);
		if (data.isPresent()) {
			Seller seller = (Seller)(data.get());
			if (seller.getProducts().contains(product)) {
				return;
			}
			product.setSeller(seller);
			product.setSellerName(seller.getUsername());
			productRepository.save(product);
			seller.addProduct(product);
			repository.save(seller);
		}
	}

	@DeleteMapping("/api/seller/{uid}/product/{pid}")
	public void deleteProductBySeller(@PathVariable("pid") int pid, @PathVariable int uid) {
		Optional<WebUser> data = repository.findById(uid);
		Optional<Product> product1 = productRepository.findById(pid);
		if (product1.isPresent() && data.isPresent()) {
			Product product = (Product)product1.get();
			Seller seller = (Seller)(data.get());
			seller.deleteProduct(product);
			repository.save(seller);
		}
	}
	
	@GetMapping("/api/checklogin")
	public void checkLogin(HttpServletResponse response) {
		if (this.session == null) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
		}
	}
	
	@GetMapping("/api/seller")
	public Iterable<Seller> findAllSeller() {
		List<WebUser> users = (List<WebUser>)repository.findAll();
		List<Seller> sellers = new ArrayList<>();
		for (WebUser user : users) {
			if (user.getRole() == "Seller") {
				Seller  seller = (Seller)user;
				sellers.add(seller);
			}
		}
		return sellers;
	}
	
	@GetMapping("/api/user/current")
	public WebUser findCurrentUser() {
		WebUser user = (WebUser)session.getAttribute("currentUser");
		return user;
	}
}
