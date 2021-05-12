package com.code.service;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RedisService {

    // @Primary 우선권으로 인해 redisClientForWrite가 불려진다.
    @Autowired
    RedisClient redisClient;

    @Qualifier("redisClientForRead")
    @Autowired
    RedisClient redisClientForRead;

    // set이 아니기에 키가 존재하면 값을 overwrite 함.
    public void addKey(String key, String value){
        try{
            StatefulRedisConnection<String, String> connection = redisClient.connect();
            RedisCommands<String, String> commands = connection.sync();
            commands.set(key, value);
            connection.close();
        }catch (Exception e){
            log.error("### Redis Set Key Error !!! ::: {}", e.getMessage());
        }
    }

    public String getValue(String key){
        String value = "";
        try{
            StatefulRedisConnection<String, String> connection = redisClientForRead.connect();
            RedisCommands<String, String> commands = connection.sync();
            value = commands.get(key);
            connection.close();
        }catch (Exception e){
            log.error("### Redis Set Key Error !!! ::: {}", e.getMessage());
        }
        return value;
    }
}
