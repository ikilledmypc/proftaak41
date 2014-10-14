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
	HashMap<String,ShoppingCart> carts = new HashMap<>();
	
	@RequestMapping(value="/setCart", method=RequestMethod.POST)
	public void setCart(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "cart", required = true) String cart){
		Gson gson = new Gson();
		ShoppingCart scart = gson.fromJson(cart, ShoppingCart.class);
		carts.replace(username, scart);
	}
	
	@RequestMapping(value="/addToCart", method=RequestMethod.POST)
	public String addToCart(@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "product", required = true) String product){
		Gson gson = new Gson();
		Product oproduct = gson.fromJson(product, Product.class);
		ShoppingCart sc = carts.get(username);
		if(sc!=null){
			sc.putProduct(oproduct);
			System.out.println("product added to cart");
			return gson.toJson(sc);
		}else{
			System.out.println("no cart found: creating one");
			ShoppingCart shopcart = new ShoppingCart();
			shopcart.putProduct(oproduct);
			carts.put(username, shopcart);
			return gson.toJson(shopcart);
		}
		
	}
	
	@RequestMapping(value="/getCart", method=RequestMethod.GET)
	public String getCart(@RequestParam(value = "username", required = true) String username){
		Gson gson = new Gson();
		return gson.toJson(carts.get(username));		
	}

}
