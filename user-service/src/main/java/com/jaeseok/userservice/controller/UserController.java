package com.jaeseok.userservice.controller;

import com.jaeseok.userservice.vo.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private Environment environment;

    @Autowired
    private Greeting greeting;

    @GetMapping("/health_check")
    public String status() {
        return "user service is working";
    }

    @GetMapping("/welcome")
    public String welcome() {
        String welcomeMessage1 = environment.getProperty("greeting.message");
        String welcomeMessage2 = greeting.getMessage();

        return welcomeMessage2;
    }
}
