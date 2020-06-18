package com.spring.demo.controller;

import com.spring.demo.retry.RetryConstant;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Auther: cdc
 * @Date: 2020/6/16 14:11
 * @Description: 重试机制的动态代理实现
 */
@Component
public class DynamicProxy implements InvocationHandler {

    private final Object subject;

    public DynamicProxy(Object realSubject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        int times = 0;
        while (times < RetryConstant.MAX_TIMES) {
            try {
                // 当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
                return method.invoke(subject, args);
            } catch (Exception e) {
                times++;

                if (times >= RetryConstant.MAX_TIMES) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }

    /**
     * 获取动态代理
     *
     * @param realSubject 代理对象
     */
    public static Object getProxy(Object realSubject) {
        //    我们要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法的
        InvocationHandler handler = new DynamicProxy(realSubject);
        return Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                realSubject.getClass().getInterfaces(), handler);
    }


}
