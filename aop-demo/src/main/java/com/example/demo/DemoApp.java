package com.example.demo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

//@Configuration   //标注一个类是配置类，spring boot在扫到这个注解时自动加载这个类相关的功能，比如前面的文章中介绍的配置AOP和拦截器时加在类上的Configuration
////启用自动配置 该框架就能够进行行为的配置，以引导应用程序的启动与运行, 根据导入的starter-pom 自动加载配置
//@EnableJpaRepositories(basePackages={"com.example.demo.repo"})
//@ComponentScan(value={"com.*","com.example.demo.controller"})//扫描组件 @ComponentScan(value = "com.spriboot.controller") 配置扫描组件的路径
@SpringBootApplication
//@EntityScan("entity对应的包路径")
@EnableAsync
@Slf4j
public class DemoApp {
    public static void main(String[] args) {
        SpringApplication.run(DemoApp.class, args);
    }

    @SneakyThrows
    static void saveRunInfo() {
        File file = new File("info.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream)) {
            outputStreamWriter.write(String.valueOf(System.getProperties()));
        }
    }
}
