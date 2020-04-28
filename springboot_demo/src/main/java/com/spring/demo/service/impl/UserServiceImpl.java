package com.spring.demo.service.impl;

import com.spring.demo.mapper.db1.UserMapper;
import com.spring.demo.mapper.db2.UserMapper2;
import com.spring.demo.pojo.User;
import com.spring.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
   // @Transactional(transactionManager = "clusterTransactionManager")
    @Override
    public void saveUser(User user) {
        saveUser1(user);
       // int i = 1/0;
        saveUser2(user);
       // userMapper1.updateUser(user);

       // userMapper2.updateUser(user);
    }


    public void saveUser1(User user) {
        userMapper1.updateUser(user);
      /*  int i = 1;
        if (i == 1) {
            throw new RuntimeException("数据源1抛出异常");
        }
        System.out.println("===============数据源1结束");*/
    }

   // @Transactional(transactionManager = "clusterTransactionManager")
    public void saveUser2(User user) {
        userMapper2.updateUser(user);
        int i = 1/0;
        /*if (i == 1) {
            throw new RuntimeException("数据源2抛出异常");
        }*/
        System.out.println("===============数据源2结束");
    }

}
