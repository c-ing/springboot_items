package com.spring.demo.interceptor_demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: cdc
 * @Date: 2020/3/25 17:19
 * @Description: 在需要登录验证的Controller的方法上使用此注解
 */

@Target({ElementType.METHOD})// 可用在方法名上
@Retention(RetentionPolicy.RUNTIME)// 运行时有效
public @interface LoginRequired {

}
