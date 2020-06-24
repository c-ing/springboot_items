package com.spring.demo.controller.example;

import com.spring.demo.service.HourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @Auther: cdc
 * @Date: 2020/6/5 11:07
 * @Description:
 */
@Controller
@RequestMapping("/test/template")
public class TemplateController {

    @Autowired
    private HourseService hourseService;

    @RequestMapping("/gethourse")
    @ResponseBody
    public String buidingHourse() throws IOException {
        hourseService.getHourse();
        return "success";
    }
}
