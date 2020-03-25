package com.spring.demo.interceptor_demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: cdc
 * @Date: 2020/3/25 17:37
 * @Description: 拦截器的测试
 */
@RestController
@RequestMapping("interceptor/test")
public class testController {

    @GetMapping("/loginInterceptor")
    @LoginRequired
    public String loginInterceptroTest() {
        return null;
    }

}
