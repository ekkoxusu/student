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

}
