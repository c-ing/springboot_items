package com.rabbit.api.rabbitapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class RabbitApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitApiApplication.class, args);
    }

}
