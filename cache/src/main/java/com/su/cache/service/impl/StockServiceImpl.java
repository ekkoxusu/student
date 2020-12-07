package com.su.cache.service.impl;

import cn.hutool.core.date.SystemClock;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.su.cache.mapper.StockMapper;
import com.su.cache.model.Stock;
import com.su.cache.request.StockQueryRequest;
import com.su.cache.request.StockUpdateRequest;
import com.su.cache.service.IRequestAsyncRouteService;
import com.su.cache.service.IStockService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xusu
 * @since 2020/12/5
 */
@Service
@Slf4j
public class StockServiceImpl extends ServiceImpl<StockMapper,Stock> implements IStockService {

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private IRequestAsyncRouteService requestAsyncRouteService;
    /**
     * 此处建议自己注入自己，防止事务和代理对象问题
     */
    @Resource
    private IStockService stockService;

    /**
     * 应该弄个静态变量或者配置文件搞[理论上查询返回速度在200ms]
     */
    private Long WAIT_TIME = 200L;
    /**
     * 应该弄个静态变量或者配置文件搞[cycleTime必须小于waitTime否则没意义]
     */
    private Long CYCLE_TIME = 20L;

    @Override
    public Boolean deleteStockCache(Long productId) {
        return redissonClient.getBucket(productId+"").delete();
    }

    @Override
    public Boolean setStockCache(Stock stock) {
        log.info("日志：-----------------进入库存修改-------------------");
        requestAsyncRouteService.process(new StockUpdateRequest(stock,stockService));
        return true;
    }

    @Override
    public Stock getStockByCache(Long productId) {
        RBucket<Stock> bucket = redissonClient.getBucket(productId + "");
        return bucket.get();
    }

    /**
     * 获取库存
     * 1. 放入内存队列
     * 2. 获取缓存数据
     *  2.1 null -> 迭代获取[每20s一次 200s后直接走DB]
     *  2.2 有值 -> 返回
     * @param productId
     * @return
     */
    @Override
    public Stock getStock(Long productId) {
        log.info("日志：-----------------进入库存查询-------------------");
        // 放入内存队列
        requestAsyncRouteService.process(new StockQueryRequest(productId,stockService));
        Long startTime = SystemClock.now();

        Stock stock = null;
        while((stock = getStockByCache(productId)) == null){
            try {
                Thread.sleep(CYCLE_TIME);
            } catch (InterruptedException e) {
                log.error("被中断了",e);
                return null;
            }
            if(SystemClock.now() - startTime >= WAIT_TIME){
                return null;
            }
        }
        log.info("日志：-----------------强制刷新缓存-------------------");
        stock = baseMapper.selectOne(new LambdaQueryWrapper<Stock>().eq(Stock::getProductId, productId));
        if(stock != null){
            // 上一个flag状态为false,但是redis中没有数据
            // [情况1.数据在redis中被删了 -> 淘汰策略导致，需要刷新一下缓存]
            // [情况2.sql太慢 -> 这种情况应当慢查询监控解决，一个队列修改查询长达200ms说明效率很低]
            requestAsyncRouteService.process(new StockQueryRequest(productId,stockService,true));
        }
        return stock;
    }
}
