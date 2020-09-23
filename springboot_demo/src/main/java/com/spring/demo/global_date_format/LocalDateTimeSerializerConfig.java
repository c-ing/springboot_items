package com.spring.demo.global_date_format;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: cdc
 * @Date: 2020/4/15 14:03
 * @Description: 全局日期格式化处理
 * 原文：https://mp.weixin.qq.com/s/aN0kXIBqc-EfpjDEXsO9Jg
 * 这种方式可支持 Date 类型和 LocalDateTime 类型并存，
 * 那么有一个问题就是现在全局时间格式是yyyy-MM-dd HH:mm:ss，但有的字段却需要yyyy-MM-dd格式咋整？
 *
 * 那就需要配合@JsonFormat注解使用，在特定的字段属性添加@JsonFormat注解即可，
 * 因为@JsonFormat注解优先级比较高，会以@JsonFormat注解标注的时间格式为主。
 */
@Configuration
public class LocalDateTimeSerializerConfig {

    @Value("${spring.jackson.date-format:yyyy-MM-dd}")
    private String pattern;

    @Bean
    public LocalDateTimeSerializer localDateTimeSerializer() {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(pattern));
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> builder.serializerByType(LocalDateTime.class, localDateTimeSerializer());
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        System.out.println(a.size());
    }
}
