package controllers;

import managers.JsonManager;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import domain.Account;

@RestController
public class TestsController {
	@RequestMapping(value = "/helloWorld", method = RequestMethod.GET)
	public String helloGet() {
		return "HELLO WORLD";
		
	}
	
	@RequestMapping(value = "/helloWorld", method = RequestMethod.POST)
	public String helloPost() {
		return "HELLO WORLD";
		
	}
}


