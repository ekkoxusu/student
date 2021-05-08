package com.su.cache.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.su.cache.listener.InitListener;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.EventListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 控制一些系统自带的Bean生成
 * @author xusu
 * @since 2020/12/5
 */
@Configurable
@Log
public class BeanFactory {


    public static final String STOCK_THREAD_POOL = "stockThreadPool";
    // 下面偷懒抉择，可以走配置文件
    /** 线程池核心池的大小*/
    public final static int CORE_POOL_SIZE = 10;


    @Resource
    public InitListener initListener;
    /**
     * 消费队列线程
     * @return
     */
    @Bean(value = STOCK_THREAD_POOL)
    public ExecutorService buildHttpApiThreadPool(){
        log.info("TreadPoolConfig创建线程数:"+CORE_POOL_SIZE+",最大线程数:"+CORE_POOL_SIZE);
        return new ThreadPoolExecutor(CORE_POOL_SIZE, CORE_POOL_SIZE, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), new ThreadFactoryBuilder().setNameFormat("stockThread").build());
    }


    @Bean
    public ServletListenerRegistrationBean<?> servletListenerRegistrationBean(){
        ServletListenerRegistrationBean<EventListener> srb = new ServletListenerRegistrationBean<>();
        srb.setListener(initListener);
        return srb;
    }

}
