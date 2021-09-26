# Multi Caffeine CacheManager

## default manager
```java
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

```

## additional manager with name
```java
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

```

## use default cache manager
```java
@Slf4j
@Service
@CacheConfig(cacheNames = {"any-name"})
public class AnyDataProvider {
    @Cacheable
    public String getAny(Long id) {
        long uuid = System.currentTimeMillis();
        log.info("get raw Any for id {} and uuid {} ",id, uuid);
        return  String.format("Any id=%d, uuid=%d", id, uuid);
    }
}
```

## use specific cache manager
```java
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
```