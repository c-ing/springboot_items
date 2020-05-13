package com.spring.demo.controller;

import com.spring.demo.pojo.User;
import com.spring.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;


/**
 * @Auther: cdc
 * @Date: 2020/4/27 16:24
 * @Description:
 */
@Api(tags = "UserController", value = "用户管理")
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /*@RequestMapping("/getList")
    @ResponseBody
    public List<User> getList() {
        return userService.selectUserList();
    }*/

    @ApiOperation("保存用户")
    @RequestMapping(value = "/saveUser",method = RequestMethod.POST)
    @ResponseBody
    public User saveUser() throws IOException {
        User user = new User();
        user.setCreTime(new Date());
        user.setName("李四");
        user.setAge(20);
        userService.saveUser(user);
       // userService.saveUser1(user);
       // userService.selectUserList();
        //int i = 1/0;
        return user;
    }
}
