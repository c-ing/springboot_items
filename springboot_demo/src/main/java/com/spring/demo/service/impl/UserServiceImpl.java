package com.spring.demo.service.impl;

import com.spring.demo.mapper.db1.UserMapper;
import com.spring.demo.mapper.db2.UserMapper2;
import com.spring.demo.pojo.User;
import com.spring.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @Auther: cdc
 * @Date: 2020/4/27 16:03
 * @Description:
 */


@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper1;

    //@Autowired
    //private UserMapper2 userMapper2;

   // @Transactional
    @Override
    public List<User> selectUserList() {
        List<User> users1 = userMapper1.selectUserList();
       // List<User> users2 = userMapper2.selectUserList();
        //users1.addAll(users2);
        return users1;
        //int i = 1/0;
        //return null;
    }

    @Transactional//(rollbackFor = Exception.class)
   // @Transactional(transactionManager = "clusterTransactionManager")
    @Override
    public void saveUser(User user){

         //   throw new RuntimeException("数据源1抛出异常");

       saveUser1(user);
      //  otherTask();
    //    int i = 1/0;
     //   saveUser2(user);
       // userMapper1.updateUser(user);

       // userMapper2.updateUser(user);
    }


   // @Transactional
    private void saveUser1(User user) {
        user.setAge(21);
        userMapper1.saveUser(user);
        //int i = 1/0;
       // if (i == 1) {
        //    throw new RuntimeException("数据源1抛出异常");
       // }
        logger.info("===============数据源1结束");
    }

    //因为文件不存在，一定会抛出一个IOException
    private void otherTask() throws IOException {
        Files.readAllLines(Paths.get("file-that-not-exist"));
    }


    // @Transactional(transactionManager = "clusterTransactionManager")
    //@Transactional
    public void saveUser2(User user) {
        user.setAge(22);
        userMapper1.saveUser(user);
       // int i = 1/0;
        /*if (i == 1) {
            throw new RuntimeException("数据源2抛出异常");
        }*/
        System.out.println("===============数据源2结束");
    }

    //@Transactional
    public void saveUser3(User user) {
        user.setAge(22);
        userMapper1.updateUser(user);
        // int i = 1/0;
        /*if (i == 1) {
            throw new RuntimeException("数据源2抛出异常");
        }*/
        System.out.println("===============数据源2结束");
    }


}
