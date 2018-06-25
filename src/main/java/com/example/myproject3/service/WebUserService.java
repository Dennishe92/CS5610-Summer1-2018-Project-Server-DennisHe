package com.example.myproject3.service;

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
import com.example.myproject3.model.Seller;
import com.example.myproject3.model.WebUser;
import com.example.myproject3.repositories.WebUserRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WebUserService {

	@Autowired
	WebUserRepository repository;
	
	@PostMapping("/api/user/Customer")
	public Customer createCustomer(@RequestBody Customer customer, HttpServletResponse response) {
		String username = customer.getUsername();
		if (repository.findUserByUsername(username) != null) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			return null;
		}
		return repository.save(customer);
	}
	
	@PostMapping("/api/user/Seller")
	public Seller createSeller(@RequestBody Seller seller, HttpServletResponse response) {
		String username = seller.getUsername();
		if (repository.findUserByUsername(username) != null) {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			return null;
		}
		
		return repository.save(seller);
	}
	
	@PostMapping("/api/user/Delivery")
	public Delivery createDelivery(@RequestBody Delivery delivery, HttpServletResponse response) {
		String username = delivery.getUsername();
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
//	
	@PostMapping("/api/login")
	public WebUser login(@RequestBody WebUser user, HttpSession session, HttpServletResponse response) {
		WebUser res = repository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (res != null) {
			session.setAttribute("currentUser", res);
			return res;
		} 
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		return null;
	}
	
	
	@GetMapping("/api/user")
	public Iterable<WebUser> findAllUsers() {
		return repository.findAll();
	}
	
//	@GetMapping("/api/user/{userId}")
//	public WebUser findUserById(@PathVariable("userId") int userId) {
//		Optional<WebUser> data = repository.findById(userId);
//		if (data.isPresent()) {
//			return data.get();
//		}
//		return null;
//	}
	
	@GetMapping("/api/user/{username}")
	public WebUser findUserByUsername(@PathVariable("username") String username) {
		WebUser data = repository.findUserByUsername(username);
		return data;
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
	
//	@GetMapping("/api/profile}")
//	public WebUser populateProfile(HttpServletRequest request, HttpServletResponse response) {
//		Optional<WebUser> user = repository.findById(1);
//		if (user != null) {
//			WebUser data = user.get();
//			return data;
//		}
//		return null;
//		Object cur = request.getServletContext().getAttribute("currentUser");
//		
//		if (cur != null) {
//			return (WebUser)cur;
//		}
//		
//		response.setStatus(HttpServletResponse.SC_CONFLICT);
//		return null;
//	}
	
	@GetMapping("/api/profile")
	public WebUser populateProfile(HttpSession session) {
		WebUser currentUser = (WebUser)session.getAttribute("currentUser");
		return currentUser;
	}
	
//	@PutMapping("/api/user/{userId}")
//	public WebUser updateUser(@PathVariable("userId") int userId, @RequestBody WebUser newUser, HttpServletResponse response) {
//		Optional<WebUser> data = repository.findById(userId);
//		if (data.isPresent()) {
//			WebUser user =  data.get();
//			user.setUsername(newUser.getUsername());//fixme
//			user.setPassword(newUser.getPassword());
//			repository.save(user);
//			return user;
//		}
//		
//		response.setStatus(HttpServletResponse.SC_CONFLICT);
//		return null;
//	}
	
	@PutMapping("/api/user/{userId}")
	public WebUser updateUser(@PathVariable("userId") int userId, @RequestBody WebUser newUser, HttpServletResponse response) {
		Optional<WebUser> user = repository.findById(userId);
		
		if (user != null) {
			WebUser data = user.get();
			data.setUsername(newUser.getUsername());
			data.setPassword(newUser.getPassword());
			repository.save(data);
			return data;
		}
		
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		return null;
	}
	

	@PutMapping("/api/profile")
	public WebUser updateProfile(@RequestBody WebUser user, HttpServletRequest request, HttpServletResponse response) {
		WebUser cur = (WebUser) request.getServletContext().getAttribute("currentUser");
		
		if (cur != null) {
			cur.setUsername(user.getUsername());
			cur.setPhone(user.getPhone());
			cur.setEmail(user.getEmail());//fixme
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
	public void logout(HttpSession session) {
		session.invalidate();
	}	
}
