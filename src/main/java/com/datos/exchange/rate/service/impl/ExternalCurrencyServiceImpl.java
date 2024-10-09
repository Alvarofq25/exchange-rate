package com.datos.exchange.rate.service.impl;

import com.datos.exchange.rate.util.CurrencyConverterUtil;
import com.datos.exchange.rate.service.ExternalCurrencyService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ExternalCurrencyServiceImpl implements ExternalCurrencyService {

    @Autowired
    private CurrencyConverterUtil currencyService;

    @SneakyThrows
    @CircuitBreaker(name = "currencyService", fallbackMethod = "fallbackExchangeRate")
    @Retry(name = "currencyService")
    public Mono<Double> getExchangeRate(String monedaOrigen, String monedaDestino) {
        return Mono.just(currencyService.calcularTipoDeCambio(monedaOrigen, monedaDestino))
                .doOnError(throwable ->
                        fallbackExchangeRate(monedaOrigen, monedaDestino, throwable));
    }

    public Mono<Double> fallbackExchangeRate(String monedaOrigen, String monedaDestino, Throwable throwable) {
        return Mono.error(new RuntimeException("Error al obtener el tipo de cambio"));
    }
}