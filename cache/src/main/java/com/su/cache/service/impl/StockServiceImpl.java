package com.su.cache.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.su.cache.mapper.StockMapper;
import com.su.cache.model.Stock;
import com.su.cache.service.IStockService;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xusu
 * @since 2020/12/5
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper,Stock> implements IStockService {

    @Resource
    private RedissonClient redissonClient;

    @Override
    public Boolean deleteStockCache(Long productId) {
        return redissonClient.getBucket(productId+"").delete();
    }

    @Override
    public Boolean setStockCache(Stock stock) {
        redissonClient.getBucket(stock.getProductId()+"").set(stock);
        return true;
    }
}
