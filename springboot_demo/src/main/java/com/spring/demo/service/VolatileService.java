package com.spring.demo.service;

import com.spring.demo.pojo.User;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Auther: cdc
 * @Date: 2020/4/15 11:28
 * @Description:
 */
public interface VolatileService {

    public void editUser(User user);

}
