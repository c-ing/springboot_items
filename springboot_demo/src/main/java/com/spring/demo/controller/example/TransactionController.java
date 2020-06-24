package com.spring.demo.controller.example;

import com.spring.demo.service.PropagationService;
import com.spring.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: cdc
 * @Date: 2020/6/18 11:42
 * @Description: spring事务传播行为的测试
 */
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private UserService userService;

    @Autowired
    private PropagationService propagationService;

    /**
     * 1. PROPAGATION_REQUIRED：
     * Spring的默认传播级别，如果上下文中存在事务则加入当前事务，如果不存在事务则新建事务执行。
     */
    @RequestMapping("required")
    public String required() {
        propagationService.requireMethod(null);
        int i = 0;
        if (i == 0) {
            try {
                throw new Exception("error");
            } catch (Exception e) {
                e.printStackTrace();
                return "fail";
            }
        }
        return "seccess1";
    }
}
