package controllers;

import managers.JsonManager;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import domain.Account;


@RestController
public class RegisterController {
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String logs(@RequestParam("json") String json) {
		JsonManager jsonManager = JsonManager.GetInstance();
		Account account = (Account) jsonManager.fromJson(json, Account.class.getClass());
		account.register();
	    return null;
	}
	
    @RequestMapping("/test2")
	public Account test2() {
		JsonManager jsonManager = JsonManager.GetInstance();
		Account a = new Account(5,"testuser","jaap","nowareinnoware 13","1234AB","noware","info@test.nl",1235663345);
		String json = jsonManager.toJson(new Account(5,"testuser","jaap","nowareinnoware 13","1234AB","noware","info@test.nl",1235663345));
	    return a;
	}
    
    @RequestMapping("/authenticateAndGet")
    public Account authenticateAndGet(@RequestParam(value="username", required=true)String username,@RequestParam(value="password", required=true)String password){
    	Account a = Account.authenticate(username, password);
    	return a;
    }

}
