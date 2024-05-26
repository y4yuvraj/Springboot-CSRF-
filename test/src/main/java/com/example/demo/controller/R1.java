package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Person;

@RestController
public class R1 {

	@GetMapping(path = "/m1")
	public String method1() {
		return "method 1 working";
	}

	@PostMapping(path = "/m1")
	public String method2() {
		return "method 2 working";
	}

	@GetMapping("/user")
	public Person getUserDetailsAfterLogin(Authentication authentication) {
		System.out.println("--------->" + authentication.getName());
		if (authentication.getName().equals("user@gmail.com")) {
			return new Person(123, "user", "1234567890", "user@gmail.com", "12345", "admin");
		} else {
			return null;
		}
	}

}
