package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.activemq.QueueConsumer;
import com.example.demo.activemq.QueueProducer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("queue")
public class QueueResource {

    @Autowired
    QueueProducer queueProducer;

    @Autowired
    QueueConsumer queueConsumer;

	@GetMapping("/produce")
	public String index(@RequestParam(value="message") String message) {
		log.info(message);
		queueProducer.sendMessage(message);
		return "Greetings from Spring Boot!";
	}
	
	@GetMapping("/consume")
	public String index2() {
		queueConsumer.consumeMessage();
		return "Greetings from Spring Boot!";
	}
}
