package com.su.cache.listener;

import com.su.cache.pool.StockRequestThreadPool;
import lombok.extern.java.Log;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 系统初始化监听器
 * @author xusu
 * @since 2020/12/5
 */
@Log
public class InitListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("========================系统初始化开始==========================================");
        log.info("========================初始化线程池/内存队列==========================================");
        StockRequestThreadPool.init();
        log.info("========================初始化热数据进缓存==========================================");

        log.info("========================系统初始化结束==========================================");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
