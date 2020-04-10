package com.java.items.demo.synchronized_demo;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Auther: cdc
 * @Date: 2020/4/2 10:26
 * @Description:
 */
public class SafeCalc {
    static long value = 0L;

    synchronized long get(String str) {
        System.out.println("========"+str + "  " + value);
        return value;
    }

    synchronized static void addOne() {
        value +=1;
        System.out.println("add one "+value);
    }

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        forkJoinPool.execute(() -> IntStream.rangeClosed(1, 10).parallel().forEach(i -> {
            SafeCalc safeCalc = new SafeCalc();
            safeCalc.get("before add");
            addOne();
           safeCalc.get("after add");
        }));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);

    }
}
