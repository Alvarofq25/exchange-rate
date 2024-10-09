package com.datos.exchange.rate;


import com.datos.exchange.rate.model.ExchangeRateRequest;
import com.datos.exchange.rate.model.ExchangeRateResponse;
import com.datos.exchange.rate.service.CurrencyRateCache;
import com.datos.exchange.rate.service.ExternalCurrencyService;
import com.datos.exchange.rate.service.impl.CurrencyConversionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CurrencyConversionServiceImplTest {

    @InjectMocks
    private CurrencyConversionServiceImpl currencyConversionService;

    @Mock
    private CurrencyRateCache rateCache;

    @Mock
    private ExternalCurrencyService externalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConvert_WithCachedRate() {
        ExchangeRateRequest request = new ExchangeRateRequest(100.0, "USD", "EUR");
        Double cachedRate = 10D;
        Double externalRate = 110.0;

        when(externalService.getExchangeRate(any(), any())).thenReturn(Mono.just(externalRate));
        when(rateCache.getRate(any())).thenReturn(Mono.just(cachedRate));
        Mono<ExchangeRateResponse> responseMono = currencyConversionService.convert(request);
        responseMono.subscribe(response -> {
            assertEquals(100.0, response.getMonto());
            assertEquals(85.0, response.getMontoConvertido());
            assertEquals("USD", response.getMonedaOrigen());
            assertEquals("EUR", response.getMonedaDestino());
            assertEquals(cachedRate, response.getValorTipoCambio());
        });
    }

    @Test
    void testConvert_WithoutCachedRate() {
        ExchangeRateRequest request = new ExchangeRateRequest(100.0, "USD", "JPY");
        Double externalRate = 110.0;

        when(rateCache.getRate(any())).thenReturn(Mono.empty());
        when(externalService.getExchangeRate(any(), any())).thenReturn(Mono.just(externalRate));

        Mono<ExchangeRateResponse> responseMono = currencyConversionService.convert(request);

        responseMono.subscribe(response -> {
            assertEquals(100.0, response.getMonto());
            assertEquals(11000.0, response.getMontoConvertido());
            assertEquals("USD", response.getMonedaOrigen());
            assertEquals("JPY", response.getMonedaDestino());
            assertEquals(externalRate, response.getValorTipoCambio());
        });
    }
}
