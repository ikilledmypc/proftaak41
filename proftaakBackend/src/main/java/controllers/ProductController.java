package controllers;

import interfaces.IDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import domain.Product;

@RestController

public class ProductController {
	
	@RequestMapping("/getAllProducts")
	public String getProducts(){
		IDatabase db = DatabaseController.getInstance();
		Gson gson = new Gson();
		ArrayList<Product> products = new ArrayList<>();
		ResultSet result = db.select("select * from Product");
		try {
			while(result.next()){
				products.add(new Product(result.getInt("productID"),result.getString("name"),result.getFloat("materialprice")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gson.toJson(products);
		
	}
	@RequestMapping(value ="/addProduct", method =  RequestMethod.POST)
	public void addProduct(@RequestParam("product") String jproduct){
		Gson gson = new Gson();
		Product p = gson.fromJson(jproduct, Product.class);
		p.addToDB();
		
	}
	
	
	
	

}
