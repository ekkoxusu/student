package com.su.cache.service.impl;

import com.su.cache.request.Request;
import com.su.cache.request.StockRequestQueue;
import com.su.cache.service.IRequestAsyncRouteService;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author xusu
 * @since 2020/12/5
 */
public class RequestAsyncRouteServiceImpl implements IRequestAsyncRouteService {
    @Override
    public void process(Request request) {
        ArrayBlockingQueue<Request> route = route(request.getProductId());
        route.add(request);
    }

    /**
     * 路由到对应的内存队列
     * @return
     */
    private ArrayBlockingQueue<Request> route(Long productId){
        StockRequestQueue instance = StockRequestQueue.getInstance();
        int h;
        h = (productId == null) ? 0 : (h = productId.hashCode()) ^ (h >>> 16);
        return instance.getQueues((instance.queueSize() - 1) & h);
    }
}