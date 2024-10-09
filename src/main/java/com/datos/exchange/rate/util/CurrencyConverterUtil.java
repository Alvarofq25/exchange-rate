package com.datos.exchange.rate.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public double calcularTipoDeCambio(String monedaOrigen, String monedaDestino)
            throws Exception {
        log.debug(" calculando tipo de cambio [origen: " + monedaOrigen + "] destino [ "+monedaDestino + "]");
        if (random.nextInt(100) < 20) {
            throw new Exception("Error al obtener el tipo de cambio.");
        }
        if (monedaOrigen.equals("USD") && monedaDestino.equals("EUR")) {
            return 0.85;
        } else if (monedaOrigen.equals("EUR") && monedaDestino.equals("USD")) {
            return 1.18;
        } else if (monedaOrigen.equals("USD") && monedaDestino.equals("JPY")) {
            return 110.00;
        } else if (monedaOrigen.equals("JPY") && monedaDestino.equals("USD")) {
            return 0.0091;
        } else {
            return 1.0;
        }
    }

    /**
     * Método para realizar la conversión de moneda.
     *
     * @param monto
     * @param monedaOrigen
     * @param monedaDestino
     * @return double valor convertido
     * @throws Exception
     */
    public double convertirMoneda(
            double monto, String monedaOrigen, String monedaDestino)
            throws Exception {
        double tipoDeCambio = calcularTipoDeCambio(monedaOrigen, monedaDestino);
        return monto * tipoDeCambio;
    }
}

