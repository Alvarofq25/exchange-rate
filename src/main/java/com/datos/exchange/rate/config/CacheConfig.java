package com.datos.exchange.rate.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Bean de configuracion de cache
 */

@Configuration
public class CacheConfig {

    @Bean
    public Cache<String, Double> cache() {
        return  Caffeine.newBuilder()
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .build();
    }
}