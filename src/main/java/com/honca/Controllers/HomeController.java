package com.honca.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String homePage(){
        return "/home";
    }
    @GetMapping("/logout")
    public String logoutForm(){
        return "logout";
    }

}
