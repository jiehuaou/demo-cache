package com.example.cache.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;



@Slf4j
@Service
@CacheConfig(cacheNames = {"hello"})
public class AnyDataProvider {
    @Cacheable
    public String getAny(Long id) {
        long uuid = System.currentTimeMillis();
        log.info("get raw Any for id {} and uuid {} ",id, uuid);
        return  String.format("Any id=%d, uuid=%d", id, uuid);
    }
}
