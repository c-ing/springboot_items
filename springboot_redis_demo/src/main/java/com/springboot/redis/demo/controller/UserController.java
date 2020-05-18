package com.springboot.redis.demo.controller;

import com.springboot.redis.demo.pojo.User;
import com.springboot.redis.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: cdc
 * @Date: 2020/5/18 13:49
 * @Description:
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/u/{id}")
    public User index(@PathVariable("id") Integer id) {
        User user = userService.getById(id);
        return user;
    }

    @GetMapping("/u/update")
    public void update() {
        User user = new User();
        user.setName("cdc");
        userService.update(user);
    }
}
