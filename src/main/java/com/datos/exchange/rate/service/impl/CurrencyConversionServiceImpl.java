package com.datos.exchange.rate.service.impl;

import com.datos.exchange.rate.model.ExchangeRateRequest;
import com.datos.exchange.rate.model.ExchangeRateResponse;
import com.datos.exchange.rate.service.CurrencyConversionService;
import com.datos.exchange.rate.service.CurrencyRateCache;
import com.datos.exchange.rate.service.ExternalCurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CurrencyConversionServiceImpl implements CurrencyConversionService {

    @Autowired
    private CurrencyRateCache rateCache;

    @Autowired
    private ExternalCurrencyService externalService;

    /**
     * calcula el tipo de cambio basado en una moneda
     * @param request
     * @return
     */
    public Mono<ExchangeRateResponse> convert(ExchangeRateRequest request) {
        log.debug("convirtiendo datos");
        String cacheKey = generateCacheKey(request);
        return rateCache.getRate(cacheKey)
                .switchIfEmpty(externalService.getExchangeRate(
                        request.getMonedaOrigen(), request.getMonedaDestino())
                        .doOnNext(rate -> rateCache.saveRate(cacheKey, rate)))
                .map(rate -> buildResponse(request, rate));
    }

    private String generateCacheKey(ExchangeRateRequest request) {
        String key = request.getMonedaOrigen() + "_" + request.getMonedaDestino() ;
        log.debug("generando key [ " + key + " ]" );
        return key;
    }

    private ExchangeRateResponse buildResponse(ExchangeRateRequest request, Double rate) {
        Double montoConvertido = request.getMonto() * rate;
        log.debug("convirtiendo response " +
                "origen ["+ request.getMonedaOrigen()+"]" +
                "destino ["+ request.getMonedaDestino()+"]" +
                "monto [ "+request.getMonto()+"]" +
                "rate [ "+ rate +"]" +
                "con el monto [ " + montoConvertido + " ]" );
        return new ExchangeRateResponse(
                request.getMonto(),
                montoConvertido,
                request.getMonedaOrigen(),
                request.getMonedaDestino(), rate);
    }
}
