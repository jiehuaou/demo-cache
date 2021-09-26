package com.example.cache.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@CacheConfig(cacheNames = {"customer-cache"}, cacheManager = "customer")
public class CustomerProvider {
    @Cacheable
    public String getCustomer(Long id) {
        long uuid = System.currentTimeMillis();
        log.info("get raw customer for id {} and uuid {} ",id, uuid);
        return  String.format("Customer id=%d, uuid=%d", id, uuid);
    }
}
