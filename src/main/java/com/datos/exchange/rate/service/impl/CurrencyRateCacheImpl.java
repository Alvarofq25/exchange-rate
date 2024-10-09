package com.datos.exchange.rate.service.impl;

import com.datos.exchange.rate.service.CurrencyRateCache;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CurrencyRateCacheImpl implements CurrencyRateCache {
    @Autowired
    private Cache<String, Double> cache;

    /**
     * Obtiene el rate que se encuentra guardado en la cache
     * @param key
     * @return
     */
    public Mono<Double> getRate(String key) {
        log.debug("obteniendo datos de la cache con la key ["+key+"]");
        return Mono.justOrEmpty(cache.getIfPresent(key));
    }

    /**
     * guarda el rate en cache
     * @param key
     * @param rate
     */
    public void saveRate(String key, Double rate) {
        log.debug("guardando datos de la cache con la key ["+key+"] y el rate ["+rate+"]");
        cache.put(key, rate);
    }
}
