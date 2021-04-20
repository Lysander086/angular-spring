package com.example.demo.service;

import com.example.demo.entity.Student;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
@Async // @Async注解可以在方法或类上使用
public class AsyncService {

    public Future<Student> get() {
        Student stu = new Student("1", 3);
        try {
            Thread.sleep(10000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return AsyncResult.forValue(stu);
    }

    public void executeRemote() {
        try {
            Thread.sleep(10000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}