package com.example.demo.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
// 指明修饰的注解，可以被例如javadoc此类的工具文档化，只负责标记，没有成员取值。
@Documented
public @interface PermissionsAnnotation {
}
