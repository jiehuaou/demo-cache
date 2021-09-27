package com.example.cache.provider;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
//@CacheConfig(cacheNames = {"messages"}, cacheManager = "logging")
public class MessageProvider {

    @Cacheable(cacheNames = {"messages"}, cacheManager = "logging")
    public String getMessage(Long id) {
        long uuid = System.currentTimeMillis();
        log.info("get raw message for id {} and uuid {} ",id, uuid);
        return  String.format("message id=%d, uuid=%d", id, uuid);
    }

    @Cacheable(cacheNames = {"notifications"}, cacheManager = "logging")
    public String getNotify(Long id) {
        long uuid = System.currentTimeMillis();
        log.info("get raw Notify for id {} and uuid {} ",id, uuid);
        return  String.format("Notify id=%d, uuid=%d", id, uuid);
    }

}
