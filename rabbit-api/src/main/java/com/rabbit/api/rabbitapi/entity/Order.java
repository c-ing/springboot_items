package com.rabbit.api.rabbitapi.entity;

public class Order {

    private String id;

    private String name;

    private String cntent;

    public Order(String id, String name, String cntent) {
        this.id = id;
        this.name = name;
        this.cntent = cntent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCntent() {
        return cntent;
    }

    public void setCntent(String cntent) {
        this.cntent = cntent;
    }
}
