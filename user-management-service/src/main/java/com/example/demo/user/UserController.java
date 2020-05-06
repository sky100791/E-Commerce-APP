package com.example.demo.user;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.cart.CartService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired CartService cartService;
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable(name = "id") String id){
		User user = new User();
		user.setUserName("John Doe");
		user.setCartList(Arrays.asList(cartService.getCart()));
		return user;
	}
}
