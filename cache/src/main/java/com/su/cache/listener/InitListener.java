package com.su.cache.listener;

import com.su.cache.pool.StockRequestThreadPool;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Set;

/**
 * 系统初始化监听器
 * @author xusu
 * @since 2020/12/5
 */
@Log
public class InitListener implements ServletContextListener {
    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("========================系统初始化开始==========================================");

        log.info("========================打印所有请求url==========================================");
        Set<RequestMappingInfo> rmSet = handlerMapping.getHandlerMethods().keySet();
        for (RequestMappingInfo rm : rmSet) {
            log.info(rm.getPatternsCondition().toString());
        }
        log.info("========================打印所有请求url结束==========================================");



        log.info("========================初始化线程池/内存队列==========================================");
        StockRequestThreadPool.init();
        log.info("========================初始化热数据进缓存==========================================");

        log.info("========================系统初始化结束==========================================");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
