package com.datos.exchange.rate.service;

import reactor.core.publisher.Mono;

public interface ExternalCurrencyService {

    Mono<Double> getExchangeRate(String monedaOrigen, String monedaDestino);

    Mono<Double> fallbackExchangeRate(String monedaOrigen, String monedaDestino, Throwable throwable);
}