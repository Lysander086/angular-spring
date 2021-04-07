package com.example.jpademo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @PostMapping("/export")
    public String export(@RequestBody String name) {
        return name;
        // return period;
    }

    @PostMapping("/export2")
    public String export2() {
        return "11";
    }
}
