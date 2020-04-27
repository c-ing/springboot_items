package com.spring.demo.service.impl;

import com.spring.demo.mapper.db1.UserMapper;
import com.spring.demo.mapper.db2.UserMapper2;
import com.spring.demo.pojo.User;
import com.spring.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: cdc
 * @Date: 2020/4/27 16:03
 * @Description:
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper1;

    @Autowired
    private UserMapper2 userMapper2;

    @Override
    public List<User> selectUserList() {
        List<User> users1 = userMapper1.selectUserList();
        List<User> users2 = userMapper2.selectUserList();
        users1.addAll(users2);
        return users1;
    }

    @Override
    public void saveUser(User user) {

    }
}
