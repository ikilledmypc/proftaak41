package controllers;

import interfaces.IDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.Product;

@RestController

public class ProductController {
	
	@RequestMapping("/getAllProducts")
	public ArrayList<Product> getProducts(){
		IDatabase db = DatabaseController.getInstance();
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
		return products;
		
	}
	
	
	
	

}
