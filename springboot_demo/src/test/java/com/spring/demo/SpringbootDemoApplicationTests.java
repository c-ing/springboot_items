package com.spring.demo;

import com.spring.demo.pojo.User;
import com.spring.demo.service.VolatileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootDemoApplicationTests {

    @Autowired
    private VolatileService volatileService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testVolatileService() {
        volatileService.editUser(new User());
    }

}
