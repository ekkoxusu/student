package com.su.cache.thread;

import com.su.cache.request.Request;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

/**
 * 库存工作线程
 * @author xusu
 * @since 2020/12/5
 */
public class StockWorkThread implements Callable {

    public ArrayBlockingQueue<Request> stockRequests;


    public StockWorkThread(ArrayBlockingQueue<Request> stockRequests) {
        this.stockRequests = stockRequests;
    }

    @Override
    public Boolean call() throws Exception {
        // 死循环执行线程任务 应当做一些机制如果任务队列长期空闲则线程让步直至任务来临[懒]
        try {
            while (true){
                // 如果队列是空的或者满了，这里会阻塞，Blocking的特色
                stockRequests.take();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
