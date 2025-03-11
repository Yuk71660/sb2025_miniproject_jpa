package com.moonya.sb2025_miniproject_jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@Log4j2
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/hello")
    public void hello(Model model) {
        log.info("model");

        List<String> list = Arrays.asList("캡틴아메리카", "토르", "스파이더맨", "아이언맨");

        model.addAttribute("list", list);
        model.addAttribute("name", "dooly");
    }
}
