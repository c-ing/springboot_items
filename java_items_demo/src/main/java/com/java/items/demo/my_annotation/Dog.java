package com.java.items.demo.my_annotation;

import java.lang.reflect.InvocationTargetException;

/**
 * @Auther: cdc
 * @Date: 2020/4/1 10:28
 * @Description:
 */
public class Dog {

    @Action
    public void say() {
        System.out.println("小狗会说话");
    }

    @Action(action = "1")
    public void sing() {
        System.out.println("小狗会唱歌");
    }

    static int foo() {
        int i = 1_0;
        while (i < 1_000_000_000) {
            i++;
        }
        return i;
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Dog dog = new Dog();
        ActionAnnotationHandle.action(dog);
    }
}
