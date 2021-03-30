package com.thoughtmechanix.licenses.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private static Boolean isInFuture(int num) {
        return num < 1;
    }

    private static String calculateAge(int n) {
        return String.valueOf(n + 1);
    }

    @GetMapping("/hi")
    public ResponseEntity<String> hi() {
        return new ResponseEntity<String>("hi", HttpStatus.OK);
    }

    @GetMapping("/ok")
    public ResponseEntity<String> ok() {
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping("/age")
    ResponseEntity<String> age(@RequestParam("yearOfBirth") int yearOfBirth) {
        if (isInFuture(yearOfBirth)) {
            return ResponseEntity.badRequest()
                    .body("Year of birth cannot be in the future");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body("Your age is " + calculateAge(yearOfBirth));
    }
}
