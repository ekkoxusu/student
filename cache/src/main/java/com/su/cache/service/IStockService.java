package com.su.cache.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.su.cache.model.Stock;

/**
 * @author xusu
 * @since 2020/12/5
 */
public interface IStockService extends IService<Stock> {

    Boolean deleteStockCache(Long productId);


    Boolean setStockCache(Stock stock);

    Stock getStockByCache(Long productId);

    /**
     * 获取库存
     * 1. 放入内存队列
     * 2. 获取缓存数据
     *  2.1 null -> 迭代获取[每20s一次 200s后直接走DB]
     *  2.2 有值 -> 返回
     * @param productId
     * @return
     */
    Stock getStock(Long productId);

}
