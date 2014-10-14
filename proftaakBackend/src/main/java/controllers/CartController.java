package controllers;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import domain.Product;
import domain.ShoppingCart;

@RestController
public class CartController {
	HashMap<String,ShoppingCart> carts;
	
	@RequestMapping(value="/setCart", method=RequestMethod.POST)
	public void setCart(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "cart", required = true) String cart){
		Gson gson = new Gson();
		ShoppingCart scart = gson.fromJson(cart, ShoppingCart.class);
		carts.replace(username, scart);
	}
	
	@RequestMapping(value="/addToCart", method=RequestMethod.POST)
	public void addToCart(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "product", required = true) String product,@RequestParam(value = "id", required = true) int id){
		Gson gson = new Gson();
		Product oproduct = gson.fromJson(product, Product.class);
		ShoppingCart sc = getCart(username);
		if(sc!=null){
			sc.putProduct(id, oproduct);
		}else{
			ShoppingCart shopcart = new ShoppingCart();
			shopcart.putProduct(id, oproduct);
			carts.put(username, shopcart);
		}
	}
	
	@RequestMapping(value="/getCart", method=RequestMethod.GET)
	public ShoppingCart getCart(@RequestParam(value = "username", required = true) String username){
		return carts.get(username);
		
	}

}
