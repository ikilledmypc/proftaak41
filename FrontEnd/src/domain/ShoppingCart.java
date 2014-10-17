package domain;

import java.util.ArrayList;
import java.util.HashMap;



public class ShoppingCart {
	HashMap<Integer,Product> products;
	
	public void putProduct(int i,Product p){
		products.put(i,p);
	}
	
	public void removeProduct(int i){
		products.remove(i);
	}
        
        public HashMap<Integer, Product> GetProducts(){
            return this.products;
        }
	

}
