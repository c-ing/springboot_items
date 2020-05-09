package com.design.patterns.behavioral_patterns.subject_observer;

/**
 * @Auther: cdc
 * @Date: 2020/5/9 13:48
 * @Description:
 */
public class Demo {

    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        subject.registerObserver(new ConcreteObserverOne());
        subject.registerObserver(new ConcreteObserverTwo());
        subject.notifyObserver(new Message());
    }
}
