package com.example.cache.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Log4j2
@Configuration
public class CacheConfig1 {


    public Caffeine caffeineConfig1() {
        return Caffeine.newBuilder()
                .expireAfterWrite(6, TimeUnit.SECONDS)
                .evictionListener((k,v, cause)->{
                    log.info("evit order  {} {} removed by {} ", k, v, cause);
                });
    }

    @Bean("order")
    public CacheManager orderManager() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager("order-cache");
        caffeineCacheManager.setCaffeine(caffeineConfig1());
        return caffeineCacheManager;
    }
}
