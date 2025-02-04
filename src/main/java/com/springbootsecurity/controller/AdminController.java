package com.springbootsecurity.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin") // Only authenticated users with the ADMIN role can access this controller's methods.
public class AdminController {
    @GetMapping("")
    public String index() {
        return "admin";
    }
}
