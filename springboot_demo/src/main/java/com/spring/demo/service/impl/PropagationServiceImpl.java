package com.spring.demo.service.impl;

import com.spring.demo.mapper.db1.UserMapper;
import com.spring.demo.pojo.User;
import com.spring.demo.service.PropagationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Auther: cdc
 * @Date: 2020/6/18 12:05
 * @Description:
 */
@Service
public class PropagationServiceImpl implements PropagationService {
    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public void requireMethod(User user) {
        User user1 = new User();
        user1.setName("requiredUser");
        user1.setAge(1);
        user1.setCreTime(new Date());
        userMapper.saveUser(user1);
        this.saveUser();
        //requireException();
    }

    @Override
    public void requireException() {
        throw new NullPointerException("肥朝假装抛出了异常");
    }


    private void saveUser() {
        User user = new User();
        user.setName("private-requiredUser");
        user.setAge(1);
        user.setCreTime(new Date());
        userMapper.saveUser(user);
    }
}
