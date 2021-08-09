package com.amela.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String getLoginForm(){
        return "/login";
    }
    @PostMapping("/login")
        public String login(@RequestParam("username") String username, @RequestParam("password")String password){
        return "redirect:/login";
    }
}
