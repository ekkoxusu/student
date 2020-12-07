package com.su.cache.request;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.su.cache.model.Stock;
import com.su.cache.service.IStockService;
import lombok.Data;
import lombok.extern.java.Log;

import java.util.Map;

/**
 * 库存请求对象
 * @author xusu
 * @since 2020/12/5
 */
@Data
@Log
public class StockQueryRequest implements Request{

    public Long productId;

    public IStockService stockService;

    public Boolean force = false;

    public StockQueryRequest(Long productId, IStockService stockService) {
        this.productId = productId;
        this.stockService = stockService;
    }

    public StockQueryRequest(Long productId, IStockService stockService, Boolean force) {
        this.productId = productId;
        this.stockService = stockService;
        this.force = force;
    }

    @Override
    public void process() {
        log.info("日志：-----------------开始查询-------------------");
        StockRequestQueue instance = StockRequestQueue.getInstance();
        Map<Long, Boolean> flagMap = instance.getFlagMap();
        // flag 去重
        // b = null 说明是老数据或者系统初始化
        // b = true 说明上一个是更新 PS：需要刷新缓存
        // b = false 说明上一个是查询
        Boolean b = flagMap.get(productId);
        if (b != null){
            if(!b){
                // b = false 但是缓存被淘汰机制清除了的话 要强制刷新[强制刷新条件 redis里面没有这个key,此处尽量由service去控制]
                if(!force){
                    return;
                }
            }
        }
        log.info("日志：-----------------开始设置缓存-------------------");
        // 如果发生了刷新缓存查询操作则设置为false[表明这个商品缓存进行了刷新操作]
        flagMap.put(productId,false);
        // 查询值
        Stock one = stockService.getOne(new LambdaQueryWrapper<Stock>().eq(Stock::getProductId, productId));
        // 放入缓存
        stockService.setStockCache(one);
    }

    @Override
    public Long getProductId() {
        return productId;
    }
}
