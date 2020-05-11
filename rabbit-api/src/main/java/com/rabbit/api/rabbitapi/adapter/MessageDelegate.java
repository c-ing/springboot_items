package com.rabbit.api.rabbitapi.adapter;

public class MessageDelegate {

    public void handleMessage(byte[] messageBody) {
        System.err.println("默认方法，消息内存："+new String(messageBody));
    }
}
