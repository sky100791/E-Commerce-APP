package com.example.demo.cart;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired RedisTemplate<String, Cart> redisTemplate;
	
	@LoadBalanced
	@GetMapping("/{id}")
	public Cart getCart(@PathVariable(name = "id") String id) {
		System.out.println("id:   " + id);
		return redisTemplate.opsForValue().get(id);
	}
	
	@PostMapping
	public Cart createCart(@RequestBody Cart cart) {
		String cartId = "" + new Date().getTime();
		Cart c = new Cart();
		c.setCartId(cartId);
		c.setCurrency(cart.getCurrency());
		c.setTotal(cart.getTotal());
		
		List<CartItem> cItems = new ArrayList<>();
		for(CartItem cartItem: cart.getCartItems()) {
			CartItem cItem = new CartItem();
			cItem.setCartItemId("" + new Date().getTime());
			cItem.setName(cartItem.getName());
			cItem.setPrice(cartItem.getPrice());
			cItem.setCurrency(cartItem.getCurrency());
			cItems.add(cItem);
		}
		c.setCartItems(cItems);
		redisTemplate.opsForValue().set(cartId, cart);
		return c;
	}
	
}
