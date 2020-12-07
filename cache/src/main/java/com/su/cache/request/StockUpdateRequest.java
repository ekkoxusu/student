package com.su.cache.request;

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
public class StockUpdateRequest implements Request{

    public Stock stock;

    public IStockService stockService;

    public StockUpdateRequest(Stock stock, IStockService stockService) {
        this.stock = stock;
        this.stockService = stockService;
    }

    @Override
    public void process() {
        log.info("日志：-----------------开始更新-------------------");
        // 如果发生了更新操作则设置为true[表明这个商品进行了更新操作]
        StockRequestQueue instance = StockRequestQueue.getInstance();
        Map<Long, Boolean> flagMap = instance.getFlagMap();
        flagMap.put(stock.getProductId(),true);
        stockService.updateById(stock);
        stockService.deleteStockCache(stock.getProductId());
        log.info("日志：-----------------更新结束-------------------");
        //TODO 如果删除失败了则丢入消息队列保证最终可以删除到缓存或者重试
            // [应当返回3种情况]
            // 1. 删除成功 2. 删除失败 3. 缓存数据不存在
    }

    @Override
    public Long getProductId() {
        return stock.getProductId();
    }
}
