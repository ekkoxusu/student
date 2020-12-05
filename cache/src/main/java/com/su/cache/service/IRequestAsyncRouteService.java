package com.su.cache.service;

import com.su.cache.request.Request;

/**
 * @author xusu
 * @since 2020/12/5
 */
public interface IRequestAsyncRouteService {
    /**
     * 请求路由，根据hash规则路由
     */
    void process(Request request);
}
