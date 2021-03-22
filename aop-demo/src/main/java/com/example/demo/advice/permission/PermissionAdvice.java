package com.example.demo.advice.permission;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
@Slf4j
public class PermissionAdvice {
    // 定义一个切面
    @Pointcut("@annotation(com.example.demo.annotation.PermissionsAnnotation)")
    private void permissionCheck() {
    }

    @Around("permissionCheck()")
    public Object permissionCheckFirst(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("===================第一个切面===================：" + System.currentTimeMillis());

        //获取请求参数，详见接口类
        Object[] objects = joinPoint.getArgs();
        Long id = ((JSONObject) objects[0]).getLong("id");
        String name = ((JSONObject) objects[0]).getString("name");
        log.info("id1->>>>>>>>>>>>>>>>>>>>>>" + id);
        log.info("name1->>>>>>>>>>>>>>>>>>>>>>" + name);

        // id小于0则抛出非法id的异常
        if (id < 0) {
            return JSON.parseObject("{\"message\":\"illegal id\",\"code\":403}");
        }
        return joinPoint.proceed();
    }
}
