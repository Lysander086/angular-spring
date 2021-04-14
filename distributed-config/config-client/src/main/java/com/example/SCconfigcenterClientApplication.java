package com.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SCconfigcenterClientApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SCconfigcenterClientApplication.class).web(true).run(args);
    }
}