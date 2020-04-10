package com.java.items.demo.countDownLatch_demo;

import java.util.Random;
import java.util.Vector;
import java.util.concurrent.*;


/**
 * @Auther: cdc
 * @Date: 2020/4/10 16:53
 * @Description: 模拟对账单系统- 优化版本 ：用 CyclicBarrier 实现线程同步
 */
public class PayCheckSystemDemoV2<P,D> {

    private static Random random = new Random();

   // private static int pos;

   // private static  int dos;

    // 订单队列
    private  Vector<P> pos;
    // 派送单队列
    private  Vector<D> dos;

    // 执行回调的线程池
    Executor executor =   Executors.newFixedThreadPool(1);
    final CyclicBarrier barrier =  new CyclicBarrier(2, ()->{
        executor.execute(()->check());
    });


    public  static Integer getDOrders(){
        System.out.println("从数据库中查询配送单");
        int dOrder = random.nextInt(9)+1;
        System.out.println("====dorder " + dOrder);
        return dOrder;
    }

    public static Integer getPOrders(){
        System.out.println("从数据库中查询未对账订单");
        int pOrder = random.nextInt(9)+1;
        System.out.println("====porder " + pOrder);
        return pOrder;
    }

    public void  check() {
        System.out.println("执行对账操作");
        P p = pos.remove(0);
        D d = dos.remove(0);  // 执行对账操作
        int diff = check(p, d);  // 差异写入差异库
        save(diff);
    }

    public  int check(P pOrder, D dOrder) {
        System.out.println("执行对账操作 2");
        return (Integer) pOrder+(Integer)dOrder;
    }

    public void checkAll() {
        //循环查询订单库
        Thread T1 = new Thread(()->{
            pos.add((P)getPOrders());
            // 等待
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        T1.start();

        Thread T2 = new Thread(()->{
            dos.add((D) getDOrders());
            // 等待
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        T2.start();

    }

    public static void save(int diff) {
        System.out.println("差异写入数据库" + diff);
    }

    public static void main(String[] args) throws InterruptedException {
        PayCheckSystemDemoV2 v2 = new PayCheckSystemDemoV2();
        v2.checkAll();
    }

}
