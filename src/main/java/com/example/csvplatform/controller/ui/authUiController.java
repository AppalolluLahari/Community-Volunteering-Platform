package com.example.csvplatform.controller.ui;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class authUiController {

    @GetMapping("/")
    public String homeView (Model model) {

        return "home";
    }

    @GetMapping("/volunteerlogin")
    public String volunteerloginView () {
        return "volunteerlogin";
    }

    @GetMapping("/organisationlogin")
    public String organisationloginView () {
        return "organisationlogin";
    }

    @GetMapping("/register")
    public String registerView (Model model) {
        return "register";
    }

}
