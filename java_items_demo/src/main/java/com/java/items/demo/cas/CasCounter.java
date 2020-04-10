package com.java.items.demo.cas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: cdc
 * @Date: 2020/4/8 10:02
 * @Description:
 */
public class CasCounter {

    private SimulateCAS simulateCAS;

    public CasCounter() {
        this.simulateCAS = new SimulateCAS();
    }

    public int getCount() {
        return simulateCAS.getValue();
    }

    public int incrementAndGet() {

        int value;
        int newValue;
        do {
            value = simulateCAS.getValue();
            newValue = value + 1;
        } while (!simulateCAS.compareAndSet(value, newValue));
        return newValue;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        CasCounter casCounter = new CasCounter();
        for (int i = 0; i < 2; i++) {
            executorService.submit(() -> {
                casCounter.incrementAndGet();
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println(casCounter.getCount());
    }

}
