package com.example.cache.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.stats.StatsCounter;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.TimeUnit;

@Log4j2
@Configuration
public class DefaultCacheConfig {
    @Bean
    public Caffeine caffeineConfig() {
        Caffeine<Object, Object> objectObjectCaffeine = Caffeine.newBuilder();
        objectObjectCaffeine
                .expireAfterWrite(3, TimeUnit.SECONDS)
                .evictionListener((k,v, cause)->{
                    log.info("evit default cache  {} [{}] by {} ", k, v, cause);
                });

        return objectObjectCaffeine;
    }

    @Primary
    @Bean()
    public CacheManager defaultManager( Caffeine caf) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager( );
        caffeineCacheManager.setCaffeine(caf);
        return caffeineCacheManager;
    }
}
