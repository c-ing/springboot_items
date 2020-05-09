package com.design.patterns.behavioral_patterns.subject_observer;

/**
 * @Auther: cdc
 * @Date: 2020/5/9 11:04
 * @Description: 消息
 */
public class Message {

    int state;
    String reault;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getReault() {
        return reault;
    }

    public void setReault(String reault) {
        this.reault = reault;
    }
}
