package com.spring.demo.service;

import com.spring.demo.pojo.User;

/**
 * @Auther: cdc
 * @Date: 2020/6/18 12:03
 * @Description:
 */
public interface PropagationService {

    void requireMethod(User user);

    void requireException();
}
