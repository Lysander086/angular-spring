package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import test.TestException;

@RestController
@RequestMapping(value = "/aop")
@Slf4j
public class AopTestController {


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Result test(@RequestParam boolean throwException) throws TestException {
// case 1

        if (throwException) {

            System.out.println("throw an exception");

            throw new TestException("mock a server exception");
        }

// case 2

        System.out.println("test OK");

        return new Result() {{
            this.setId(111);
            this.setName("mock a Result");
        }};

    }

    public static class Result {

        private int id;

        private String name;

        public int getId() {

            return id;

        }

        public void setId(int id) {

            this.id = id;

        }

        public String getName() {

            return name;

        }

        public void setName(String name) {

            this.name = name;

        }

    }

}