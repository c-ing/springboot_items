package com.springboot.redis.demo.stock;

/**
 * @Auther: cdc
 * @Date: 2020/5/22 15:02
 * @Description:
 */
public interface RequestAsyncProcessService {

    void process(Request request);
}
