package com.datos.exchange.rate.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Clase utilitaria para simular el calculo de tipo de cambio
 */
@Slf4j
@Service
public class CurrencyConverterUtil {

    private static final Random random = new Random();

    /**
     * Simulación de tipos de cambio.
     *
     * @param monedaOrigen
     * @param monedaDestino
     * @return
     * @throws Exception
     */
    private static final Map<String, Double> exchangeRates = new HashMap<>();

    static {
        exchangeRates.put("USD_EUR", 0.85);
        exchangeRates.put("EUR_USD", 1.18);
        exchangeRates.put("USD_JPY", 110.00);
        exchangeRates.put("JPY_USD", 0.00091);
    }

    public double calcularTipoDeCambio(String monedaOrigen, String monedaDestino) {
        String key = monedaOrigen + "_" + monedaDestino + monedaDestino;
        return exchangeRates.getOrDefault(key, 1.0);
    }
}

