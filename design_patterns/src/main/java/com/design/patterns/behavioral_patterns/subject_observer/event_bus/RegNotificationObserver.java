package com.design.patterns.behavioral_patterns.subject_observer.event_bus;

import com.google.common.eventbus.Subscribe;

/**
 * @Auther: cdc
 * @Date: 2020/5/9 14:44
 * @Description:
 */
public class RegNotificationObserver  {//implements RegObserver

    @Subscribe  // 注册到eventBus
    //@Override
    public void handleRegSuccess(Long userId) {
        System.out.println("============================");
    }
}
