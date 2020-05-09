package com.design.patterns.behavioral_patterns.subject_observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: cdc
 * @Date: 2020/5/9 11:18
 * @Description: 具体的被观察者
 */
public class ConcreteSubject implements Subject {

    private List<Observer> observers = new ArrayList<Observer>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removerObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(Message message) {
        observers.stream().forEach(o->{
            o.update(message);
        });
    }
}
