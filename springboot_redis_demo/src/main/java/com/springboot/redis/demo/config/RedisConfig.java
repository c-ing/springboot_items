package com.springboot.redis.demo.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @Auther: cdc
 * @Date: 2020/5/18 10:39
 * @Description: 通过重写redisTemplate的属性改变默认序列化策略
 * 缓存设置参考：https://mp.weixin.qq.com/s/faPorQjAE9c-UPl8YSU7nw
 */
@Configuration
@EnableCaching // 开启Spring Cache 的缓存功能支持
public class RedisConfig {

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(jackson2JsonRedisSerializer);

        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jackson2JsonRedisSerializer);

        return template;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate<String, Object> template) {
        //基本配置
/*
        RedisCacheConfiguration defaultCacheConfiguration = RedisCacheConfiguration
                .defaultCacheConfig()
                // 设置key为String
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(template.getStringSerializer()))
                // 设置value为自动转json的Object
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(template.getValueSerializer()))
                // 不缓存 null
                .disableCachingNullValues()
                // 缓存数据保存1小时
                .entryTtl(Duration.ofHours(1));
*/

        RedisCacheManager redisCacheManager = RedisCacheManager.RedisCacheManagerBuilder
                // Redis 连接工厂
                .fromConnectionFactory(template.getConnectionFactory())
                // 缓存配置 : 默认1小时
                .cacheDefaults(getCacheConfigurationWithTtl(template,60*60))
                // key 为cache_user的60秒
                .withCacheConfiguration("cache_user",getCacheConfigurationWithTtl(template,60))
                .withCacheConfiguration("cache_post",getCacheConfigurationWithTtl(template,120))
                // 配置同步修改或删除 put/evict
                .transactionAware()
                .build();

        return redisCacheManager;
    }

    private RedisCacheConfiguration getCacheConfigurationWithTtl(RedisTemplate<String, Object> template, long seconds) {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                // 设置key为String
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(template.getStringSerializer()))
                // 设置value为自动转json的Object
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(template.getValueSerializer()))
                // 不缓存 null
                .disableCachingNullValues()
                // 缓存数据保存1小时
                .entryTtl(Duration.ofSeconds(seconds));
    }
}
