package com.spring.demo.common_aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Auther: cdc
 * @Date: 2020/4/15 15:21
 * @Description: 环绕通知
 */
@Aspect
@Component
public class LoginAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoginAspect.class);

    @Around(value = "execution(* com.spring.demo.controller.example.VolatileController.editUser())")
    public Object loginAspect(ProceedingJoinPoint jp) throws Throwable {
        Object[] args = jp.getArgs();
        logger.info("登录开始");
        Object result = jp.proceed();
        logger.info("登录结束");
        logger.info(result.toString());
        return result;
    }
}
