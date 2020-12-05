package com.su.cache.request;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author xusu
 * @since 2020/12/5
 */
public class StockRequestQueue {


    public List<ArrayBlockingQueue<Request>> queues = new ArrayList<>();


    public Boolean addQueue(ArrayBlockingQueue<Request> stockRequests){
        return queues.add(stockRequests);
    }


    private StockRequestQueue() {
    }

    private static class Single{
        private static StockRequestQueue INSTANCE = new StockRequestQueue();

        private static StockRequestQueue getInstance(){
            return INSTANCE;
        }
    }

    public static StockRequestQueue getInstance(){
        return StockRequestQueue.Single.getInstance();
    }

    public int queueSize(){
        return queues.size();
    }

    public ArrayBlockingQueue<Request> getQueues(int i) {
        return queues.get(i);
    }
}
