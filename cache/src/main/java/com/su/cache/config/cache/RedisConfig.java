package com.su.cache.config.cache;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author xusu
 * @since 2020/12/7
 */
@Configuration
public class RedisConfig {

    @Resource
    private RedisProperties redisProperties;

    /**
     * redis集群配置
     * @return
     */
    @Bean
    public RedissonClient redisSentinelConfiguration() {
        Config config = new Config();
        config.setTransportMode(TransportMode.EPOLL);
        //TODO 理论上应该可以用什么办法将rediProperties所有参数转化为config，暂时写一大堆set
        config.useSingleServer()
                .setAddress(String.format("redis://%s:%s",redisProperties.getHost()+"",redisProperties.getPort()+""))
                .setPassword(redisProperties.getPassword())
                .setTimeout(new Long(redisProperties.getTimeout().toMillis()).intValue());
        return Redisson.create(config);
    }
}
