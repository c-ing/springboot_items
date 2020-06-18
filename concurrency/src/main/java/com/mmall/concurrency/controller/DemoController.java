package com.mmall.concurrency.controller;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: cdc
 * @Date: 2020/6/9 11:23
 * @Description:
 */
@Slf4j
@RestController
public class DemoController {

    @GetMapping("/demo")
    public String demo()throws InterruptedException {
        //模拟业务耗时处理流程
        //Thread.sleep(20*1000L);
        try {
            int i = 1/0;
        } catch (Exception e) {
            log.error("",e);
            //e.printStackTrace();
        }
        System.out.println("==================继续执行");
        return "hello";
    }

    // JMeter测试
    @GetMapping(value = "/threadLocal/test")
    public String performanceTest(@RequestParam(value="name", defaultValue="") String name) {
        log.info("进入测试，参数name的值为:{}", name);
        if (StringUtils.isEmpty(name)) {
            return "name cannot be null";
        } else {
            return RandomUtil.randomString(16);
        }
    }
}
