package com.example.demo.cart;

import java.io.Serializable;

public class CartItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cartItemId;
	private String name;
	private float price;
	private String currency;
	
	public CartItem() {
		super();
	}

	public String getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(String cartItemId) {
		this.cartItemId = cartItemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
}
