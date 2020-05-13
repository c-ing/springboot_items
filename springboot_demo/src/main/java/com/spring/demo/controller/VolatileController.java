package com.spring.demo.controller;

import com.spring.demo.pojo.User;
import com.spring.demo.service.VolatileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: cdc
 * @Date: 2020/4/15 11:28
 * @Description:
 */
@Api(tags = "VolatileController", value = "volatile管理")
@Controller
@RequestMapping("/volatile")
public class VolatileController {
    @Autowired
    private VolatileService volatileService;

    @RequestMapping("/editUser")
    @ResponseBody
    public User editUser() throws InterruptedException {
        User user = new User();
        CountDownLatch countDownLatch = new CountDownLatch(4);
        user.setCreTime(new Date());
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++) {
            executor.execute(()->{
               // User user = new User();
                volatileService.editUser(user);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        return user;
    }
}
