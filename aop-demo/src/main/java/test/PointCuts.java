package test;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {

    @Pointcut(value = "within(test.*)")
    public void aopDemo() {

    }
}