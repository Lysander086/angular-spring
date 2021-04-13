package com.example.distributedconfig;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SCconfigCenterApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SCconfigCenterApplication.class).web(true).run(args);
    }
}