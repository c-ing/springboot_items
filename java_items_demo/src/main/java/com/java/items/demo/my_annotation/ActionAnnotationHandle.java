package com.java.items.demo.my_annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Auther: cdc
 * @Date: 2020/4/1 10:17
 * @Description: 自定义注解处理器，根据反射拿到Object的所有方法Method，
 * 然后遍历拿到Method上面的Action注解，判断action属性值，为0就调用，不为0就不调用. 贼简单..
 */
public class ActionAnnotationHandle {

    public static void action(Object obj) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = obj.getClass().getDeclaredMethods();
        for (Method mt : methods) {
            if (mt.getName().equals("main")) {
                continue;
            }
            Action ac = mt.getAnnotation(Action.class);
            String result = ac.action();
            if (result.equals("0")) {
                // 0 表示要执行
                mt.invoke(obj);
            }
        }
    }

}
