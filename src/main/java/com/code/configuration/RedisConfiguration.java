package com.code.configuration;

import io.lettuce.core.RedisClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RedisConfiguration {

    @Bean
    @Primary // Bean 생성 우선권 부여
    public RedisClient redisClientForWrite(){
        RedisClient redisClient = RedisClient.create("redis://localhost:8000");
        return redisClient;
    }

    @Bean
    public RedisClient redisClientForRead(){
        RedisClient redisClient = RedisClient.create("redis://localhost:8001");
        return redisClient;
    }

}
