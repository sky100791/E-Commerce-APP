package com.example.demo.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired ProductRepository productRepository;
	
	@CrossOrigin
	@GetMapping("/recommendations")
	public List<Product> getProductRecommendation() {
		return productRepository.findAll();
	}
	
	@HystrixCommand(fallbackMethod = "getDefaultProductRecommendationByTextSearch", 
			commandProperties = @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"))
	@GetMapping("/recommendations/{textSearched}")
	public List<Product> getProductRecommendationByTextSearch(@PathVariable(name = "textSearched") String textSearched) throws InterruptedException{
		Thread.sleep(5000);
		return productRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Product getProduct(@PathVariable(name = "id") String id) throws Exception {
		Optional<Product> product = productRepository.findById(id);
		if(product.isPresent()) {
			throw new Exception("Product not found");
		}
		else {
			return product.get();
		}
	}
	
	@PostMapping
	public Product addProduct(@RequestBody Product product) {
		Product p = new Product();
		p.setCurrency(product.getCurrency());
		p.setDescription(product.getDescription());
		p.setName(product.getName());
		p.setPrice(product.getPrice());
		return productRepository.save(p);
	}
	
	@PutMapping("/{id}")
	public Product updateProduct(@RequestBody Product p, @PathVariable(name = "id") String id) throws Exception{
		Optional<Product> product = productRepository.findById(id);
		if(product.isPresent()) {
			throw new Exception("Product not found");
		}
		else {
			Product updatedProduct = product.get();
			updatedProduct.setCurrency(p.getCurrency());
			updatedProduct.setDescription(p.getDescription());
			updatedProduct.setName(p.getName());
			updatedProduct.setPrice(p.getPrice());
			return productRepository.save(updatedProduct);
		}
	}
	
	public List<Product> getDefaultProductRecommendationByTextSearch(String textSearched) throws Exception{
		List<Product> productList = new ArrayList<Product>();
		Product p = new Product();
		p.setId("000000");
		p.setName("000000");
		productList.add(p);
		return productList;
	}
}
