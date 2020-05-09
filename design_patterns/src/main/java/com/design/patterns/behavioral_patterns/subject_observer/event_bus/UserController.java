package com.design.patterns.behavioral_patterns.subject_observer.event_bus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: cdc
 * @Date: 2020/5/9 14:34
 * @Description:
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;



   // @Autowired
    private EventBus eventBus;

    private static final int DEFAULT_EVENTBUS_THREAD_POOL_SIZE = 20;

    public UserController() {
        //eventBus = new EventBus(); // 同步阻塞模式
        eventBus = new AsyncEventBus(Executors.newFixedThreadPool(DEFAULT_EVENTBUS_THREAD_POOL_SIZE));
        List<Object> observers = new ArrayList<>();
        observers.add(new RegNotificationObserver());
        observers.add(new RegPromotionObserver());
        setRegObserver(observers);
    }

    public void setRegObserver(List<Object> observers) {
        for (Object observer : observers) {
            System.out.println("注册观察者");
            eventBus.register(observer);
        }
    }

    @RequestMapping("/register")
    @ResponseBody
    public Long register(/*String telephone, String password*/) {
        String telephone = null;
        String password = null;
        long userId = userService.register(telephone, password);
        eventBus.post(userId);
        return userId;
    }
}
