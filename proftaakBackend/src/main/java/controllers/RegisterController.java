package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import interfaces.IDatabase;
import managers.JsonManager;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import domain.Account;
import domain.Photographer;


@RestController
public class RegisterController {
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@RequestParam("account") String json) {
		JsonManager jsonManager = JsonManager.GetInstance();
		Account account = (Account) jsonManager.fromJson(json, Account.class);
		account.register();
	    return null;
	}
	
	@RequestMapping(value = "/registerPhotographer", method = RequestMethod.POST)
	public String registerPhotographer(@RequestParam("photographer") String json) {
		JsonManager jsonManager = JsonManager.GetInstance();
		Photographer photographer = (Photographer) jsonManager.fromJson(json, Photographer.class);
		photographer.registerPhotographer();
	    return null;
	}
	
	@RequestMapping("/checkAvailable")
	public boolean available(@RequestParam(value = "username", required = true)String username){
		IDatabase db = DatabaseController.getInstance();
		ResultSet rst = db.select("select username from account where username = '"+ username +"'");
		try {
			if(!rst.next()){
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
    @RequestMapping("/test2")
	public Account test2() {
		Photographer f = new Photographer("username","name","address","zipcode","city","email","telephone","compname","1");
		
	    return (Account) f;
	}
    
    

}
