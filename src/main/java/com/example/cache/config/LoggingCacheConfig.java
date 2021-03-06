package com.example.cache.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Ticker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * use SimpleCacheManager & CaffeineCache
 */

@Slf4j
@Configuration
public class LoggingCacheConfig {

    @Bean("logging")
    public CacheManager cacheManager() {
        CaffeineCache messageCache = buildCache("messages",  4);
        CaffeineCache notificationCache = buildCache("notifications",  8);
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(Arrays.asList(messageCache, notificationCache));
        return manager;
    }

    private CaffeineCache buildCache(String name,  int secondToExpire) {
        return new CaffeineCache(name, Caffeine.newBuilder()
                .expireAfterWrite(secondToExpire, TimeUnit.SECONDS)
                .maximumSize(100)
                //.ticker(ticker)
                .evictionListener((k,v, cause)->{
                    log.info("evit logging cache  {} [{}] by {} ", k, v, cause);
                })
                .build());
    }

//    @Bean
//    public Ticker ticker() {
//        return Ticker.systemTicker();
//    }
}
