package com.pizzeria.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String getIndex() {
        return "index"; // index.html w templates
    }

    @GetMapping("/pizzas")
    public String getIndex() {
        return "pizzas"; // index.html w templates
    }


}
