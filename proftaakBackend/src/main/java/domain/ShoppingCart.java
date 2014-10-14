package domain;

import java.util.ArrayList;
import java.util.HashMap;



public class ShoppingCart {
	HashMap<Integer,Product> products = new HashMap<>();
	//ArrayList<Product> products = new ArrayList<>();
	
	public void putProduct(Product p){
		products.put(products.size(),p);
		//products.add(p);
	}
	
	public void removeProduct(int i){
		products.remove(i);
	}
	

}
