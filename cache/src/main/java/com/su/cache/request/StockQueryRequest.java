package com.su.cache.request;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.su.cache.model.Stock;
import com.su.cache.service.IStockService;
import lombok.Data;

/**
 * 库存请求对象
 * @author xusu
 * @since 2020/12/5
 */
@Data
public class StockQueryRequest implements Request{

    public Long productId;

    public IStockService stockService;

    public StockQueryRequest(Long productId, IStockService stockService) {
        this.productId = productId;
        this.stockService = stockService;
    }

    @Override
    public void process() {
        // flag 去重

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
