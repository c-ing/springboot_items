package com.springboot.redis.demo.stock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

/**
 * @Auther: cdc
 * @Date: 2020/5/22 14:22
 * @Description: 执行真正请求业务工作的线程类
 */
public class RequestProcessorThread implements Callable<Boolean> {

    private static final Logger logger = LoggerFactory.getLogger(RequestProcessorThread.class);

    /**
     *
     */
    private ArrayBlockingQueue<Request> queue;

    public RequestProcessorThread(ArrayBlockingQueue<Request> queue) {
        this.queue = queue;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            while (true) {
                // 不断消费请求的业务
                Request request = queue.take();
            if (request instanceof ProductInventoryDBUpdateRequest) {
                logger.info("=========操作数据库请求");
            }else{
                logger.info("=====更新缓存请求");
            }
            request.process();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
