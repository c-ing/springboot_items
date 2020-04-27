package com.spring.demo.pojo;

import java.util.Date;

/**
 * @Auther: cdc
 * @Date: 2020/4/15 11:30
 * @Description:
 */
public class User {

    private Integer id;

    private volatile String name;

    private Integer age;

    private Date creTime;

    public Date getCreTime() {
        return creTime;
    }

    public void setCreTime(Date creTime) {
        this.creTime = creTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
