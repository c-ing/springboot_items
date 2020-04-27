package com.spring.demo.service.impl;

import com.spring.demo.pojo.User;
import com.spring.demo.service.VolatileService;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Auther: cdc
 * @Date: 2020/4/15 11:29
 * @Description:
 */
@Service
public class VolatileServiceImpl implements VolatileService {

    @Override
    public void editUser(User user) {
        String name = user.getName();
        System.out.println(Thread.currentThread().getName()+"==beforeSet==1==="+name);
        user.setName(Thread.currentThread().getName() +"aa");
        String name2 = user.getName();
        System.out.println(Thread.currentThread().getName() + "====afterSet==="+ name2);
    }

}
