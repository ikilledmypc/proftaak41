package controllers;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import domain.Account;
import domain.Photographer;

@RestController
public class AccountController {
	private HashMap<String,Account> loggedInUsers = new HashMap<>();
	
	@RequestMapping("/authenticateAndGet")
    public Account authenticateAndGet(@RequestParam(value="username", required=true)String username,@RequestParam(value="password", required=true)String password){
    	Account a = Account.authenticate(username, password);
    	if(a!=null){
    		loggedInUsers.put(username, a);
    	}
    	return a;
    }
    
    @RequestMapping("/authenticateAndGetPhotographer")
    public Photographer authenticateAndGetPhotographer(@RequestParam(value="username", required=true)String username,@RequestParam(value="password", required=true)String password){
    	Photographer a = Photographer.authenticate(username, password);
    	return a;
    }
    
    public Account IsLoggedIn(String username){
    	return loggedInUsers.get(username);
    }

}
