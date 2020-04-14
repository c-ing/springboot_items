package com.java.items.demo.future_task_demo;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: cdc
 * @Date: 2020/4/14 10:19
 * @Description:
 */
public class T2Task implements Callable<String> {

    @Override
    public String call() throws Exception {

        System.out.println("T2洗茶壶...");
        TimeUnit.SECONDS.sleep(1);

        System.out.println("T2洗茶杯...");
        TimeUnit.SECONDS.sleep(1);

        System.out.println("T2: 拿茶叶...");
        TimeUnit.SECONDS.sleep(10);
        System.out.println("T2: 拿到茶叶");

        return "龙井";
    }
}
