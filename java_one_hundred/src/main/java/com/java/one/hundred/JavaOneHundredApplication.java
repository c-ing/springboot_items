package com.java.one.hundred;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class})
@EnableTransactionManagement
public class JavaOneHundredApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaOneHundredApplication.class, args);
    }

}
