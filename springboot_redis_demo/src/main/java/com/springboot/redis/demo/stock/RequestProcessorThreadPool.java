package com.springboot.redis.demo.stock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: cdc
 * @Date: 2020/5/22 11:46
 * @Description: 线程池处理类
 * 单例
 */
public class RequestProcessorThreadPool {

    /**
     * 线程池
     */
    private ExecutorService threadPool = Executors.newFixedThreadPool(10);

    private RequestProcessorThreadPool() {

        RequestQueue requestQueue = RequestQueue.getInstance();

        /**
         * 给线程池中加入任务队列
         */
        for (int i = 0; i < 10; i++) {
            ArrayBlockingQueue<Request> queue = new ArrayBlockingQueue<>(100);
            requestQueue.addQueue(queue);
            threadPool.submit(new RequestProcessorThread(queue));
        }
    }

    /**
     * 单例有很多种方式去实现：采取绝对线程安全的一种方式
     * 静态内部类的方式，去初始化单例
     * 创建单例线程池
     * @author Administrator
     *
     */
    private static class Singleton {
        private static RequestProcessorThreadPool instance;

        /**
         * 静态代码块
         * 特点：随着类的加载而执行，且只执行一次，并优先于主函数。用于给类初始化的。
         */
        static {
            instance = new RequestProcessorThreadPool();
        }

        public static RequestProcessorThreadPool getInstance() {
            return instance;
        }

    }

    /**
     * jvm的机制去保证多线程并发安全
     * 因为Singleton 是 静态内部类的方式，保证了RequestProcessorThreadPool只会创建一次。
     * 当第二次之后调用就都是拿之前创建好的对象，保证了多线程并发安全。
     * 内部类的初始化，一定只会发生一次，不管多少个线程并发去初始化
     *
     * @return
     */
    public static RequestProcessorThreadPool getInstance() {
        return Singleton.getInstance();
    }

    /**
     * 初始化的便捷方法
     */
    public static void init() {
        getInstance();
    }
}
