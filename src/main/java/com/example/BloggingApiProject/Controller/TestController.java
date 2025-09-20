package com.example.BloggingApiProject.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class TestController {
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
