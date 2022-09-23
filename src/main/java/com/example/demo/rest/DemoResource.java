package com.example.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoResource {


	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	
}
