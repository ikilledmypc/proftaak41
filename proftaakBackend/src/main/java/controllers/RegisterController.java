package controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import domain.Account;


@RestController
public class RegisterController {
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String logs(@RequestParam("json") String json) {
		
	    return null;
	}

}
