package com.java.items.demo.cas;

/**
 * @Auther: cdc
 * @Date: 2020/4/8 10:03
 * @Description:
 */
public class SimulateCAS {

    private int value;

    public synchronized int getValue() {
        return value;
    }

    public boolean compareAndSet(int expect, int newValue) {
        synchronized (this) {
            if (value == expect) {
                value = newValue;
                return true;
            }
        }
        return false;
    }
}
