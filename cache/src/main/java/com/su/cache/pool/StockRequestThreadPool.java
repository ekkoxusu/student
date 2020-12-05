package com.su.cache.pool;

import com.su.cache.config.BeanFactory;
import com.su.cache.config.SpringTool;
import com.su.cache.request.Request;
import com.su.cache.request.StockRequestQueue;
import com.su.cache.thread.StockWorkThread;
import lombok.extern.java.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;

/**
 * 库存请求线程池
 * @author xusu
 * @since 2020/12/5
 */
@Log
public class StockRequestThreadPool {

    /**
     * 线程池
     */

    private final ExecutorService stockThreadPool;


    private StockRequestThreadPool() {
        stockThreadPool = (ExecutorService) SpringTool.getBean(BeanFactory.STOCK_THREAD_POOL);
        StockRequestQueue queues = StockRequestQueue.getInstance();
        for (int i = 0; i <= BeanFactory.CORE_POOL_SIZE; i++) {
            // 初始化100大小的内存队列
            ArrayBlockingQueue<Request> blockQueue = new ArrayBlockingQueue<>(128);
            queues.addQueue(blockQueue);
            stockThreadPool.submit(new StockWorkThread(blockQueue));
        }
    }

    public static void init(){
        log.info("========================初始化开始库存内存线程池==========================================");
        Single.getInstance();
        log.info("========================初始化完成库存内存线程池==========================================");
    }

    private static class Single{
        private static StockRequestThreadPool INSTANCE = new StockRequestThreadPool();

        private static StockRequestThreadPool getInstance(){
            return INSTANCE;
        }
    }

    private static StockRequestThreadPool getInstance(){
        return Single.getInstance();
    }

}
