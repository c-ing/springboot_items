package com.springboot.redis.demo.stock;

/**
 * @Auther: cdc
 * @Date: 2020/5/22 14:03
 * @Description: 封装的请求接口，所有的请求方法都会继承它
 */
public interface Request {

    void process();

    Integer getProductId();

    boolean isForceRfresh();
}
