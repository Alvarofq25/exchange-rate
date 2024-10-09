package com.datos.exchange.rate.service;

import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


public interface CurrencyRateCache {

    Mono<Double> getRate(String key) ;

    void saveRate(String key, Double rate);
}
