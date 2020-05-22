package com.springboot.redis.demo;

import com.springboot.redis.demo.stock.InitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootRedisDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRedisDemoApplication.class, args);
    }

    /**
     * 注册监听器
     * @return
     */
    @SuppressWarnings({"rawtypes","unchecked"})
    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new InitListener());
        return servletListenerRegistrationBean;
    }

}
