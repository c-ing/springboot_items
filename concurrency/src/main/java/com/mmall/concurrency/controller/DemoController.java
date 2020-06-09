package com.mmall.concurrency.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: cdc
 * @Date: 2020/6/9 11:23
 * @Description:
 */
@RestController
public class DemoController {

    @GetMapping("/demo")
    public String demo()throws InterruptedException {
        //模拟业务耗时处理流程
        Thread.sleep(20*1000L);
        System.out.println("==================继续执行");
        return "hello";
    }
}
