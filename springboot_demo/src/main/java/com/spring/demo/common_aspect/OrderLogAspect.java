package com.spring.demo.common_aspect;

import com.spring.demo.annotation.OrderLog;
import com.spring.demo.common.Action;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Auther: cdc
 * @Date: 2020/6/23 13:56
 * @Description:
 */
@Aspect
@Component
public class OrderLogAspect {

    /**
     * 定义切入点
     */
    @Pointcut("@annotation(com.spring.demo.annotation.OrderLog)")
    public void actionLog() {
    }

    /**
     * 新增结果返回后触发
     *
     * @param point
     * @param returnValue
     */
    @AfterReturning(returning = "returnValue", pointcut = "actionLog() && @annotation(orderLog)")
    public void doAfterReturning(JoinPoint point, Object returnValue, OrderLog orderLog) {
        // 注解中的类型
        String enumKey = orderLog.action();
        System.out.println(Action.valueOf(enumKey).getValue());
    }
}
