package com.design.patterns.behavioral_patterns.subject_observer.event_bus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: cdc
 * @Date: 2020/5/9 15:27
 * @Description:
 */
//@Component
public class MyEventBus {



    public MyEventBus() {
        System.out.println("myEventBus 初始化");
        List<Object> observers = new ArrayList<>();
        observers.add(new RegNotificationObserver());
        observers.add(new RegPromotionObserver());
        register(observers);
    }

    private static final int DEFAULT_EVENTBUS_THREAD_POOL_SIZE = 20;

    private final static EventBus eventBus = new AsyncEventBus(Executors.newFixedThreadPool(DEFAULT_EVENTBUS_THREAD_POOL_SIZE));

    /**
     * 触发同步事件
     *
     * @param event
     */
    public static void post(Object event) {
        eventBus.post(event);
    }
    /**
     * 注册事件处理器
     *
     * @param
     */
    public static void register(List<Object> observers) {
        for (Object observer : observers) {
            System.out.println("注册观察者");
            eventBus.register(observer);
        }
        //eventBus.register(handler);
    }
    /**
     * 注销事件处理器
     *
     * @param handler
     */
    public static void unregister(Object handler) {
        eventBus.unregister(handler);
    }
}
