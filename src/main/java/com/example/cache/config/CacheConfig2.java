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
public class CacheConfig2 {

    public Caffeine caffeineConfig2() {
        return Caffeine.newBuilder()
                .expireAfterWrite(9, TimeUnit.SECONDS)
                .evictionListener((k,v, cause)->{
                    log.info("evit customer  {} {} by {} ", k, v, cause);
                });
    }

    @Bean("customer")
    public CacheManager customerManager() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager( "customer-cache");
        caffeineCacheManager.setCaffeine(caffeineConfig2());
        return caffeineCacheManager;
    }
}
