package com.mmall.concurrency.example.threadLocal;

/**
 * @Auther: cdc
 * @Date: 2020/6/9 16:41
 * @Description:
 */
public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id) {
        requestHolder.set(id);
    }

    public static Long getId() {
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }
}
