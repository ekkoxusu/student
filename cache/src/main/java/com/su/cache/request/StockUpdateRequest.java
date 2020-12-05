package com.su.cache.request;

import com.su.cache.model.Stock;
import com.su.cache.service.IStockService;
import lombok.Data;

/**
 * 库存请求对象
 * @author xusu
 * @since 2020/12/5
 */
@Data
public class StockUpdateRequest implements Request{

    public Stock stock;

    public IStockService stockService;

    public StockUpdateRequest(Stock stock, IStockService stockService) {
        this.stock = stock;
        this.stockService = stockService;
    }

    @Override
    public void process() {
        stockService.updateById(stock);
        if (stockService.deleteStockCache(stock.getProductId())) {
            //TODO 如果删除失败了则丢入消息队列保证最终可以删除到缓存
            // [应当返回3种情况]
            // 1. 删除成功 2. 删除失败 3. 缓存数据不存在
        }
    }

    @Override
    public Long getProductId() {
        return stock.getProductId();
    }
}
