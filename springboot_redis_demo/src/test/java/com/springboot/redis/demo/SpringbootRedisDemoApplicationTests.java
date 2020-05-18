package com.springboot.redis.demo;

import com.springboot.redis.demo.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class SpringbootRedisDemoApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void contextLoads() throws InterruptedException {

        stringRedisTemplate.opsForValue().set("name","hello world");
        System.out.println("----------------" + stringRedisTemplate.opsForValue().get("name"));

      //  Thread.sleep(3000);

        redisTemplate.opsForValue().set("name","hello world2");
        System.out.println("---------------"+redisTemplate.opsForValue().get("name"));

        redisUtil.expire("name", 3);

    }

}
