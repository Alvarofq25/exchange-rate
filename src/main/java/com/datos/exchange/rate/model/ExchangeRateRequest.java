package com.datos.exchange.rate.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Objeto de entrada para calcular tipo de cambio
 */
@AllArgsConstructor
@Data
public class ExchangeRateRequest implements Serializable {

    private Double monto;
    private String monedaOrigen;
    private String monedaDestino;

}
