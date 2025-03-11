package com.moonya.sb2025_miniproject_jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/")
@Log4j2
public class HomeController {

    @GetMapping("")
    public String home() {
        return "index";
    }
}
