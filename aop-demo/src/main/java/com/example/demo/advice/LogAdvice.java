package com.example.demo.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAdvice {

    /* 多种 配置方法 */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    private void logAdvicePointcut(){}
    
    @Before("logAdvicePointcut()")
    public void logAdvice(){
        String fn = "[logAdvice]";
        log.info(fn);
    }

}
