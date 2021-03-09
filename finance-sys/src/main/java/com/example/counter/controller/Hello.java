package com.example.counter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @Autowired
    private StringRedisTemplate template;

    @RequestMapping("/redisTest")
    public String redisTest(){
        template.opsForValue().set("test:Hello","World");
        return template.opsForValue().get("test:Hello");
    }


    @RequestMapping("/hello")
    public String hello() {
        return "hello world!";
//        return "" + DbUtil.getId();
    }

}
