package com.java.items.demo.my_annotation;

import java.lang.annotation.*;

/**
 * @Auther: cdc
 * @Date: 2020/4/1 10:13
 * @Description: 定义一个Action注解，action属性为0时，表示调用，为其他时表示不被调用.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {

    public String action() default "0";
}
