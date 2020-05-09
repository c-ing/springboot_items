package com.spring.demo.service;

import com.spring.demo.pojo.User;

import java.io.IOException;
import java.util.List;

/**
 * @Auther: cdc
 * @Date: 2020/4/27 16:03
 * @Description:
 */
public interface UserService {

    List<User> selectUserList();

    void saveUser(User user) throws IOException;

    //void saveUser1(User user);

    void saveUser2(User user);

    void saveUser3(User user);
}
