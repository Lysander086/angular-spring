package com.example.demo.controller;


import com.example.demo.entity.Student;
import com.example.demo.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* reference link: https://www.cnblogs.com/teach/p/14127024.html*/
@Controller
@RequestMapping("async")
public class ControllerAsyncTest {


    @Autowired
    private AsyncService asyncService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public Student get() {
        try {
            long start = System.currentTimeMillis();        //调用带有返回值的get方法
            Future<Student> result = asyncService.get();        //调用无返回值的executeRemote方法
            asyncService.executeRemote();

            Student student = result.get();
            long end = System.currentTimeMillis();
            System.out.println("执行时间：" + (end - start));
            return student;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}