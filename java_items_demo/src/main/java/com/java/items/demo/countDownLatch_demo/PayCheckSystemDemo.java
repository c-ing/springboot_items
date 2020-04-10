package com.java.items.demo.countDownLatch_demo;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 * @Auther: cdc
 * @Date: 2020/4/10 16:53
 * @Description: 模拟对账单系统
 */
public class PayCheckSystemDemo {

    private static Random random = new Random();

    private static int pos;

    private static  int dos;

    public  static int getDOrders(){
        System.out.println("从数据库中查询配送单");
        int dOrder = random.nextInt(9)+1;
        System.out.println("====dorder " + dOrder);
        return dOrder;
    }

    public static int getPOrders(){
        System.out.println("从数据库中查询未对账订单");
        int pOrder = random.nextInt(9)+1;
        System.out.println("====porder " + pOrder);
        return pOrder;
    }

    public static int check(int pOrder, int dOrder) {
        System.out.println("执行对账操作");
        return pOrder+dOrder;
    }

    public static void save(int diff) {
        System.out.println("差异写入数据库" + diff);
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建2个线程的线程池
        Executor executor = Executors.newFixedThreadPool(2);
        // 计数器初始值为2
        CountDownLatch latch = new CountDownLatch(2);
        executor.execute(()->{
            pos = getPOrders();
            latch.countDown();
            System.out.println(latch.getCount());
        });

        executor.execute(()->{
            dos = getDOrders();
            latch.countDown();
            System.out.println(latch.getCount());
        });
        // 等待两个查询操作结束
        latch.await();
        int diff = check(pos, dos);
        save(diff);
    }

}
