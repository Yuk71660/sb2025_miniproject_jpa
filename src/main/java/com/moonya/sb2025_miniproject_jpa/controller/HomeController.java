package com.moonya.sb2025_miniproject_jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Sample {
    String s1, s2;

    public String getS1() {
        return this.s1;
    }

    public String getS2() {
        return this.s2;
    }
}

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
        model.addAttribute("name", "둘리");
    }

    @GetMapping("/inline")
    public void inline(Model model) {
        List<String> strList = IntStream.range(1, 10).mapToObj(i -> "data" + i).collect(Collectors.toList());
        model.addAttribute("strList", strList);

        Map<String, String> map = new HashMap<>();
        map.put("A", "AAA");
        map.put("B", "BBB");
        model.addAttribute("map", map);

        model.addAttribute("str", "abc");

        Sample sample = new Sample();
        sample.s1 = "dooly";
        sample.s2 = "douner";

        model.addAttribute("obj", sample);
    }
}
