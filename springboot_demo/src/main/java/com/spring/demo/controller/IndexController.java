package com.spring.demo.controller;

import com.spring.demo.common.ReturnT;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Auther: cdc
 * @Date: 2020/6/24 15:31
 * @Description:
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Model model) {

        Map<String, Object> dashboardMap = null;//xxlJobService.dashboardInfo();
        model.addAllAttributes(dashboardMap);

        return "index";
    }

    @RequestMapping("/toLogin")
    public String toLogin(HttpServletRequest request, HttpServletResponse response) {
        /*if (loginService.ifLogin(request, response) != null) {
            return "redirect:/";
        }*/
        return "login";
    }

    @RequestMapping(value="login", method= RequestMethod.POST)
    @ResponseBody
    public ReturnT<String> loginDo(HttpServletRequest request, HttpServletResponse response, String userName, String password, String ifRemember){
        boolean ifRem = (ifRemember!=null && ifRemember.trim().length()>0 && "on".equals(ifRemember))?true:false;
        return ReturnT.SUCCESS;//loginService.login(request, response, userName, password, ifRem);
    }
}
