package com.datos.exchange.rate.service;

import com.datos.exchange.rate.model.ExchangeRateRequest;
import com.datos.exchange.rate.model.ExchangeRateResponse;
import reactor.core.publisher.Mono;

public interface CurrencyConversionService {

    Mono<ExchangeRateResponse> convert(ExchangeRateRequest request);
}
