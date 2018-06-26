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
import com.example.myproject3.model.Product;
import com.example.myproject3.model.Seller;
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
	
//	@GetMapping("/api/seller/{sid}/product")
//	public Iterable<Product> findProductsBySeller(@PathVariable("sid") int sid) {
//		Optional<WebUser> seller1 = webUserRepository.findById(sid);
//		if(seller1.isPresent()) {
//			Seller seller = (Seller)seller1.get();
//			return seller.getProducts();
//		}
//		
//		return null;
//	}
	
//	@GetMapping("/api/seller/product")
//	public Iterable<Product> findProductsBySeller(HttpSession session) {
//		Seller seller = (Seller)session.getAttribute("currentUser");
//	    return seller.getProducts();
//	}
	
//	@PostMapping("/api/seller/{sid}/product/{pid}")
//	public void addProductBySeller(@PathVariable("sid") int sid, @PathVariable("pid") int pid) {
//		Optional<WebUser> seller1 = webUserRepository.findById(sid);
//		Optional<Product> product1 = productRepository.findById(pid);
//		if (seller1.isPresent() && product1.isPresent()) {
//			Seller seller = (Seller)seller1.get();
//			Product product = (Product)product1.get();
//			seller.addProduct(product);
//			webUserRepository.save(seller);
//		}
//	}

//	@PostMapping("/api/seller/product/{pid}")
//	public void addProductBySeller(HttpSession session, @PathVariable("pid") int pid) {
//		Seller seller = (Seller)session.getAttribute("currentUser");
//		Optional<Product> product1 = productRepository.findById(pid);
//		if (product1.isPresent()) {
//			Product product = (Product)product1.get();
//			if (seller.getProducts().contains(product)) {
//				return;
//			}
//			seller.addProduct(product);
//			webUserRepository.save(seller);
//		}
//	}
	
	@Autowired
	ProductRepository productRepository;
	
//	@PostMapping("/api/seller/product")
//	public void addProductBySeller(HttpSession session, @RequestBody Product product) {
//		Seller seller = (Seller)session.getAttribute("currentUser");
//			if (seller.getProducts().contains(product)) {
//				return;
//			}
//			product.setSeller(seller);
//			product.setSellerName(seller.getUsername());
//			productRepository.save(product);
//			seller.addProduct(product);
//			webUserRepository.save(seller);
//	}
	
//	// when customer like recipe, save recipe into like list of customer
//		@PostMapping("/api/customer/like/recipe/{apiId}")
//		public void likeRecipeByCustomer(@PathVariable("apiId") int apiId, HttpSession session) {
//			Customer customer = (Customer)session.getAttribute("currentUser");
//			for(Recipe recipe : customer.getLikedRecipes()) {
//				if (recipe.getApiId() == apiId) {
//					return;
//				}
//			}
//			Recipe recipe = new Recipe();
//			recipe.setApiId(apiId);
//			recipe.likeCustomer(customer);
//			customer.likeRecipe(recipe);
//			webUserRepository.save(customer);
//			recipeRepository.save(recipe);
//		}
		
	
//	@DeleteMapping("/api/seller/{sid}/product/{pid}")
//	public void deleteProductBySeller(@PathVariable("sid") int sid, @PathVariable("pid") int pid) {
//		Optional<WebUser> seller1 = webUserRepository.findById(sid);
//		Optional<Product> product1 = productRepository.findById(pid);
//		if (seller1.isPresent() && product1.isPresent()) {
//			Seller seller = (Seller)seller1.get();
//			Product product = (Product)product1.get();
//			seller.deleteProduct(product);
//			webUserRepository.save(seller);
//		}
//	}
	
//	@DeleteMapping("/api/seller/product/{pid}")
//	public void deleteProductBySeller(HttpSession session, @PathVariable("pid") int pid) {
//		Seller seller = (Seller)session.getAttribute("currentUser");
//		Optional<Product> product1 = productRepository.findById(pid);
//		if (product1.isPresent()) {
//			Product product = (Product)product1.get();
//			seller.deleteProduct(product);
//			webUserRepository.save(seller);
//		}
//	}
}
