package controllers;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

import domain.Product;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import domain.Account;
import domain.ShoppingCart;

@RestController
public class OrderController {
	@RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
	public void placeOrder(@RequestParam("cart") String cart,
			@RequestParam("accountID") String accountID) {
		Gson gson = new Gson();
		ShoppingCart sc = gson.fromJson(cart, ShoppingCart.class);
		DatabaseController db = DatabaseController.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		// get current date time with Date()
		try {
			System.out
					.println("INSERT INTO `order`(`date`, `accountID`) VALUES ("
							+ new GregorianCalendar().getTime().toString()
							+ "," + accountID + ")");
			ResultSet rs = db
					.insert("INSERT INTO `order`(`date`, `accountID`) VALUES ('"
							+ dateFormat.format(Calendar.getInstance()
									.getTime()) + "'," + accountID + ")");

			rs.next();
			int orderID = rs.getInt(1);
	        ArrayList<Product> products = sc.GetProducts();
	        for(Product p:products){
	        	db.insert("insert into order_photo_product (orderID,numberOf,photoID,productID) values("+orderID+","+p.getAmount()+","+p.getPhoto().getPhotoID()+","+p.getProductID()+")");
	        }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@RequestMapping(value = "/getNumberOfOrderedPhotos", method = RequestMethod.GET)
	public String getNumberOfOrderedPhotos(@RequestParam(value = "accountID", required = true) int accountID){
		DatabaseController db = DatabaseController.getInstance();
		int numberOf = 0;
		ResultSet rst = db.select("SELECT photoID FROM PhotoGroup_photo WHERE photogroupID = (SELECT photogroupID FROM photogroup WHERE accountID = '"+ accountID +"')");
		try {
			while(rst.next()){
				ResultSet rst2 = db.select("SELECT photoproductID FROM photo_product WHERE productID = '0' AND photoID = '" + rst.getInt("photoID") + "'");
				while(rst2.next()){
					ResultSet rst3 = db.select("SELECT numberOf FROM order_photo_product WHERE photoproductID ='" + rst2.getInt("photoproductID") + "'");
					while(rst3.next()){
						numberOf = numberOf + rst3.getInt("numberOf");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();
		return Integer.toString(numberOf);
	}
	
	@RequestMapping(value = "/getNumberOfOrderedProducts", method = RequestMethod.GET)
	public String getNumberOfOrderedProducts(@RequestParam(value="accountID", required=true)int accountID){
		DatabaseController db = DatabaseController.getInstance();
		int numberOf = 0;
		ResultSet rst = db.select("SELECT photoID FROM PhotoGroup_photo WHERE photogroupID = (SELECT photogroupID FROM photogroup WHERE accountID = '"+ accountID +"')");
		try {
			while(rst.next()){
				ResultSet rst2 = db.select("SELECT photoproductID FROM photo_product WHERE productID <> '0' AND photoID = '" + rst.getInt("photoID") + "'");
				while(rst2.next()){
					ResultSet rst3 = db.select("SELECT numberOf FROM order_photo_product WHERE photoproductID ='" + rst2.getInt("photoproductID") + "'");
					while(rst3.next()){
						numberOf = numberOf + rst3.getInt("numberOf");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.closeConnection();
		return Integer.toString(numberOf);
	}
}
