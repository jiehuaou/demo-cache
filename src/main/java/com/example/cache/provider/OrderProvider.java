package com.example.cache.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@CacheConfig(cacheNames = {"order-cache"}, cacheManager = "order")
public class OrderProvider {
    @Cacheable
    public String getOrder(Long id) {
        long uuid = System.currentTimeMillis();
        log.info("get raw Order for id {} and uuid {} ",id, uuid);
        return  String.format("Order id=%d, uuid=%d", id, uuid);
    }
}
