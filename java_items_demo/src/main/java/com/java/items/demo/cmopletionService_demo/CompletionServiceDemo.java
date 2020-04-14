package com.java.items.demo.cmopletionService_demo;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Auther: cdc
 * @Date: 2020/4/14 11:30
 * @Description: 使用了completionService实现了一个询价应用的核心功能，然后要计算出最低报价并返回
 */
public class CompletionServiceDemo {

    static AtomicReference<Integer> m = new AtomicReference<>(Integer.MAX_VALUE);

    static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static int getPriceByS1() {

        return 1;
    }

    public static int getPriceByS2() {
        return 2;
    }

    public static int getPriceByS3() {
        return 3;
    }

    public static int getPriceByS4() {
        return 4;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
        // 计数器初始值为2
        CountDownLatch latch = new CountDownLatch(4);
        cs.submit(() -> getPriceByS1());
        cs.submit(() -> getPriceByS2());
        cs.submit(() -> getPriceByS3());
        cs.submit(() -> getPriceByS4());
        for (int i = 0; i < 4; i++) {
            executor.execute(()->{
                Integer r = null;
                try {
                    r = cs.take().get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("保存数据库"+r);
                m.set(Integer.min(m.get(),r));
                latch.countDown();
            });
        }
        latch.await();
        System.out.println("==================m:");
        System.out.println(m);
    }
}
