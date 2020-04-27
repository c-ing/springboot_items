package com.spring.demo.controller;

import com.spring.demo.pojo.User;
import com.spring.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: cdc
 * @Date: 2020/4/27 16:24
 * @Description:
 */

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getList")
    @ResponseBody
    public List<User> getList() {
        return userService.selectUserList();
    }
}
