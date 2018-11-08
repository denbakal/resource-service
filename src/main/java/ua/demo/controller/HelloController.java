package ua.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot (Resource Server)!";
    }

    @RequestMapping("/ping")
    public String ping() {
        return "ping ping ping second resource server";
    }

}
