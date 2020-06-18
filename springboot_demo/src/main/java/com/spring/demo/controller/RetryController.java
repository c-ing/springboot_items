package com.spring.demo.controller;

import com.spring.demo.pojo.User;
import com.spring.demo.retry.UserServiceProxyImpl;
import com.spring.demo.service.UserService;
import com.spring.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: cdc
 * @Date: 2020/6/16 13:45
 * @Description:
 */
@RestController
@RequestMapping("/retry")
public class RetryController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;


    @RequestMapping("/demo")
    public List<User> retryDemo() {
        //UserServiceProxyImpl userServiceProxy = new UserServiceProxyImpl();
        //return userServiceProxy.selectUserList();
       // UserService userService2 = new UserServiceImpl();

       // UserService proxyUserService = (UserService) DynamicProxy.getProxy(userService2);
        //return proxyUserService.selectUserList();
    }
}
