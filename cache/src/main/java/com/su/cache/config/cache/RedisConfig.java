package com.su.cache.config.cache;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.redisson.spring.starter.RedissonProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xusu
 * @since 2020/12/7
 */
@Configuration
@EnableConfigurationProperties(RedissonProperties.class)
public class RedisConfig {
    /**
     * redis集群配置
     * @return
     */
    @Bean
    public RedissonClient redisSentinelConfiguration(RedisProperties redisProperties) {
        Config config = new Config();
        config.setTransportMode(TransportMode.NIO);
        //TODO 理论上应该可以用什么办法将redisProperties所有参数转化为config，暂时写一大堆set
        config.useSingleServer()
                .setAddress(String.format("redis://%s:%s",redisProperties.getHost()+"",redisProperties.getPort()+""))
                .setPassword(redisProperties.getPassword())
                .setTimeout(new Long(redisProperties.getTimeout().toMillis()).intValue());
        return Redisson.create(config);
    }
}
