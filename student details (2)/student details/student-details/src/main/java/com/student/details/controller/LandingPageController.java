package com.student.details.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LandingPageController {
    @GetMapping("/landing")
    public String landingPage(){
        return "landingAndProfilePages/landingPage";

    }
}


