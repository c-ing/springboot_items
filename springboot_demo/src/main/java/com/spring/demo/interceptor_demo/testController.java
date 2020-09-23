package com.spring.demo.interceptor_demo;

import com.spring.demo.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

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

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);

        User user = new User();
        user.setId(1);
        user.setAge(1);
        user.setCreTime(new Date());
        user.setName("aa");

        User user2 = new User();
        user2.setId(1);
        user2.setAge(2);
        user2.setCreTime(new Date());
        user2.setName("aa");

        User user3 = new User();
        user3.setId(1);
        user3.setAge(2);
        user3.setCreTime(new Date());
        user3.setName("bb");

        List<User> list1 = new ArrayList<>();
        list1.add(user);
        list1.add(user2);
        list1.add(user3);

        Map<String,Integer> map = list1.stream().collect(Collectors.groupingBy(u->u.getId()+"-"+u.getName(),Collectors.summingInt(User::getAge)));
        Map<String,User> map2 = list1.stream().collect(Collectors.toMap(u->u.getId()+"-"+u.getName(),a->a,(k1,k2)->k2));


        Optional<Integer> isFinish = list.stream().filter(a->a<=1).findFirst();
        System.out.println(!isFinish.isPresent());
    }

}
