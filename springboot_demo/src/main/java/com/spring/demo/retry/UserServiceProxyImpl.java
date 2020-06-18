package com.spring.demo.retry;

import com.spring.demo.pojo.User;
import com.spring.demo.service.UserService;
import com.spring.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: cdc
 * @Date: 2020/6/16 13:40
 * @Description:
 */
@Service("userServiceProxyImpl")
@Primary
public class UserServiceProxyImpl implements UserService {

    @Autowired
    private UserService userService = new UserServiceImpl();

    @Override
    public List<User> selectUserList(){
        int times = 0;
        while (times < RetryConstant.MAX_TIMES) {
            try {
                System.out.println("===================");
                System.out.println("time = " + times);
                if (times > 0) {
                    Thread.sleep(1000);
                }
                int i = 1/0;
                return userService.selectUserList();
            } catch (Exception e) {
                times++;

                if(times >= RetryConstant.MAX_TIMES) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public void saveUser2(User user) {

    }

    @Override
    public void saveUser3(User user) {

    }
}
