package com.springboot.redis.demo.service.impl;

import com.springboot.redis.demo.pojo.User;
import com.springboot.redis.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Auther: cdc
 * @Date: 2020/5/18 13:42
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    private static  final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Cacheable(cacheNames = "cache_user",key = "'user_'+#id")
    @Override
    public User getById(Integer id) {
        logger.info("进来查库了---------->{}",id);
        User user = new User();
        user.setId(1);
        user.setName("cdc_"+id);
        return user;
    }

    @Override
    @CacheEvict(cacheNames = "cache_user",allEntries = true)
    public void update(User user) {
        logger.info("更新user {}",user);
    }
}
