package com.spring.demo.common;

/**
 * @Auther: cdc
 * @Date: 2020/6/23 13:36
 * @Description: 记录日志，操作类型的枚举类
 */
public enum Action {
    ADD("add","新增"),
    EDIT("edit","修改"),
    DELETE("delete","删除");

    private String action;
    private String value;

    Action(String action, String value) {
        this.action = action;
        this.value = value;
    }

    public String getAction() {
        return action;
    }

    public String getValue() {
        return value;
    }
}
