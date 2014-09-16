package controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import domain.Account;


@RestController
public class RegisterController {
	@RequestMapping("/register")
    public Account register(
    		@RequestParam(value="username", required=true) String username,
    		@RequestParam(value="name", required= true)String name,
    		@RequestParam(value="address", required= true)String address,
    		@RequestParam(value="zipcode", required = true)String zipcode,
    		@RequestParam(value="city", required = true)String city,
    		@RequestParam(value="email", required = true)String email,
    		@RequestParam(value="telephone", required = true)String telephone) {
		
		return null;
    }

}
