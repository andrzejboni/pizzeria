package com.pizzeria.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class AppUserController {
    @GetMapping(path = "/profile")
    public String getProfile() {
        return "user/profile";
    }

}
