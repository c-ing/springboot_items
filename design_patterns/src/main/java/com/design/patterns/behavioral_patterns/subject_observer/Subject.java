package com.design.patterns.behavioral_patterns.subject_observer;

/**
 * @Auther: cdc
 * @Date: 2020/5/9 11:03
 * @Description: 被观察者
 */
public interface Subject {

    void registerObserver(Observer observer);

    void removerObserver(Observer observer);

    void notifyObserver(Message message);
}
