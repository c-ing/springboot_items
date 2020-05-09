package com.design.patterns.behavioral_patterns.subject_observer;

/**
 * @Auther: cdc
 * @Date: 2020/5/9 13:46
 * @Description: 具体观察者1
 */
public class ConcreteObserverOne implements Observer {

    @Override
    public void update(Message message) {
        //todo: 获取消息通知，执行自己的逻辑
        System.out.println("ConcreteObserverOne is notified");
    }
}
