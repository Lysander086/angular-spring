package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.annotation.PermissionsAnnotation;
import org.springframework.web.bind.annotation.*;

@RestController("/")
public class AopController {

    @GetMapping("/getTest")
    public JSONObject hello(){
        return JSON.parseObject("{\"message\":\"SUCCESS\",\"code\":200}");
    }

    @PostMapping(value = "/postTest")
    public JSONObject postTest(@RequestParam("id") String id){
        return  JSON.parseObject("{\"message\":\"SUCCESS\",\"code\":200}");
    }

    @PermissionsAnnotation()
    @RequestMapping(value = "/permission/check", method = RequestMethod.POST)
    public JSONObject permissionCheck(@RequestBody JSONObject req){
        return JSON.parseObject("{\"message\":\"SUCCESS\",\"code\":200}");
    }


}
