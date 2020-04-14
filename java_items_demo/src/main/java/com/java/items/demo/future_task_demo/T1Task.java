package com.java.items.demo.future_task_demo;

import java.util.concurrent.*;

/**
 * @Auther: cdc
 * @Date: 2020/4/14 10:13
 * @Description: T1Task 需要执行的任务：洗水壶，烧开水，泡茶
 */
public class T1Task implements Callable<String> {

    FutureTask<String> ft2;

    //  T1 任务需要 T2 任务的 FutureTas
    public T1Task(FutureTask<String> ft2) {
        this.ft2 = ft2;
    }

    @Override
    public String call() throws Exception {
        System.out.println("T1: 洗水壶");
        TimeUnit.SECONDS.sleep(1);

        System.out.println("T1: 烧开水");
        TimeUnit.SECONDS.sleep(5);
        // 获取T2线程的茶叶
        String tf = ft2.get();
        System.out.println("T1: 拿到茶叶："+tf);

        System.out.println("T1: 泡茶...");
        return "上茶："+tf;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建任务T2的futureTask
        FutureTask<String> ft2 = new FutureTask<>(new T2Task());
        //创建任务T1的futureTask
        FutureTask<String> ft1 = new FutureTask<>(new T1Task(ft2));
        ExecutorService es = Executors.newFixedThreadPool(2);
        es.submit(ft1);
        es.submit(ft2);
        String result = ft1.get();
        System.out.println(result);
        es.shutdown();
    }
}
