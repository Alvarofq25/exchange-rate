package com.datos.exchange.rate.controller;

import com.datos.exchange.rate.model.ExchangeRateRequest;
import com.datos.exchange.rate.model.ExchangeRateResponse;
import com.datos.exchange.rate.service.CurrencyConversionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


/**
 * Clase que contiene el controlados a ser invocado para la conversion de moneda
 */
@RestController
@RequestMapping("/api/conversion")
@Slf4j
public class CurrencyConversionController {

    @Autowired
    private CurrencyConversionService conversionService;

    /**
     * calcula el tipo de cambio segun una moneda
     * @param request
     * @return
     */
    @PostMapping
    public Mono<ResponseEntity<ExchangeRateResponse>> convertCurrency(
            @RequestBody ExchangeRateRequest request) {
        log.debug("Iniciando el metodo de conversion");
        return conversionService.convert(request)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().build()));
    }
}