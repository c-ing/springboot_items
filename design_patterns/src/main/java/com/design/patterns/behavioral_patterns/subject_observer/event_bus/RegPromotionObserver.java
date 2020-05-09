package com.design.patterns.behavioral_patterns.subject_observer.event_bus;

import com.google.common.eventbus.Subscribe;

/**
 * @Auther: cdc
 * @Date: 2020/5/9 14:43
 * @Description: 注册完发放体验金
 */
public class RegPromotionObserver {// implements RegObserver {

    @Subscribe // 注册到eventBus
    //@Override
    public void handleRegSuccess(Long userId) {
        System.out.println("////////////////////////////");
    }
}
