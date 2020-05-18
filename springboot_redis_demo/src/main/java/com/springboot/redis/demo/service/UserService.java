package com.springboot.redis.demo.service;

import com.springboot.redis.demo.pojo.User;

/**
 * @Auther: cdc
 * @Date: 2020/5/18 13:41
 * @Description:
 */
public interface UserService {

    User getById(Integer id);

    void update(User user);
}
