package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.activemq.QueueProducer;

@RestController
public class DemoResource {

    @Autowired
    QueueProducer queueProducer;

	@GetMapping("/")
	public String index() {
		queueProducer.sendMessage("Test12321321312");
		return "Greetings from Spring Boot!";
	}
	
}
