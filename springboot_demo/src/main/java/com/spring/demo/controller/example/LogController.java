package com.spring.demo.controller.example;

import com.spring.demo.annotation.OrderLog;
import com.spring.demo.common.Action;
import com.spring.demo.common.ReturnT;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Auther: cdc
 * @Date: 2020/6/23 13:50
 * @Description: 日志测试控制层
 */
@RestController
@RequestMapping("/log")
public class LogController {



    @OrderLog(action = "ADD")
    @RequestMapping("/test/{id}")
    public String logTest(@PathVariable(name = "id") Integer id) {
        return "Hello"+id;
    }

    @RequestMapping(value="login", method= RequestMethod.POST)
    @ResponseBody
    public ReturnT<String> loginDo(HttpServletRequest request, HttpServletResponse response, String userName, String password, String ifRemember){
        boolean ifRem = (ifRemember!=null && ifRemember.trim().length()>0 && "on".equals(ifRemember))?true:false;
        return ReturnT.SUCCESS;//loginService.login(request, response, userName, password, ifRem);
    }
}
