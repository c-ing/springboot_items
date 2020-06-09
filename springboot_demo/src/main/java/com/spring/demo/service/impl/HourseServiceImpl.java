package com.spring.demo.service.impl;

import com.spring.demo.mapper.db1.UserMapper;
import com.spring.demo.my_template.HourseTemplate;
import com.spring.demo.pojo.User;
import com.spring.demo.service.HourseService;
import com.spring.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;

/**
 * @Auther: cdc
 * @Date: 2020/6/5 11:13
 * @Description:
 */

@Service
public class HourseServiceImpl extends HourseTemplate implements HourseService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;


    @Override
    protected void buildWalls() {
        System.out.println("building Walls");
    }

    @Override
    protected void buildPillars() {
        System.out.println("save users");
        User user = new User();
        user.setAge(11);
        user.setName("11");
        user.setCreTime(new Date());
        userService.saveUser(user);
        int i= 1/0;
    }

    @Transactional
    @Override
    public String getHourse() throws IOException {
        this.buildHouse();
        System.out.println("=============get a big hourse");
        User user = new User();
        user.setAge(33);
        userMapper.updateUser(user);

        //int i = 1/0;
       // this.exceptionMethod();
        return "get a big hourse";
    }

    private void exceptionMethod() {
        int i = 1/0;
    }
}
