package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/aop")
public class AopController {

    @GetMapping("/getTest")
    public JSONObject hello(){
        return JSON.parseObject("{\"message\":\"SUCCESS\",\"code\":200}");
    }

    @PostMapping(value = "/postTest")
    public JSONObject postTest(@RequestParam("id") String id){
        return  JSON.parseObject("{\"message\":\"SUCCESS\",\"code\":200}");
    }


}
