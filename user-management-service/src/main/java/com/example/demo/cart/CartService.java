package com.example.demo.cart;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "cart-service")
public interface CartService {
	
	@GetMapping("/cart/261dce78-ee1d-45eb-90c1-b6d133545839")
	public Cart getCart();

}
