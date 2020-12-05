package com.su.cache.request;

/**
 * @author xusu
 * @since 2020/12/5
 */
public interface Request {
    /**
     * 处理接口
     */
    void process();

    Long getProductId();
}
