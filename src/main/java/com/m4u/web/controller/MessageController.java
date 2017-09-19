package com.m4u.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

	@RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
