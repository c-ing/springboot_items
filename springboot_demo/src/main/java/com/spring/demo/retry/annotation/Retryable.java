package com.spring.demo.retry.annotation;

import java.lang.annotation.*;

/**
 * @Auther: cdc
 * @Date: 2020/6/16 14:53
 * @Description: 注解可在方法上使用，定义需要重试的次数
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Retryable {

    /**
     * Exception type that are retryable.
     * @return exception type to retry
     */
    Class<? extends Throwable> value() default RuntimeException.class;

    /**
     * 包含第一次失败
     * @return the maximum number of attempts (including the first failure), defaults to 3
     */
    int maxAttempts() default 3;
}
