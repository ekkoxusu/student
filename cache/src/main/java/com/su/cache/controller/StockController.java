package com.su.cache.controller;

import com.su.cache.model.Stock;
import com.su.cache.model.vo.Response;
import com.su.cache.service.IStockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xusu
 * @since 2020/12/7
 */
@RestController
@RequestMapping("stock")
public class StockController {

    @Resource
    private IStockService stockService;

    /**
     * 获取商品库存
     * 1. 将请求让入异步service
     * 2. 获取数据
     * @param productId
     * @return
     */
    @GetMapping("get")
    public Response<Stock> get(Long productId){
        return Response.sussce(stockService.getStock(productId));
    }
    /**
     *  设置商品缓存
     */
    @GetMapping("set")
    public Response<Boolean> setStock(@RequestBody Stock stock){
        return Response.sussce(stockService.setStockCache(stock));
    }
}
