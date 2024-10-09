package com.datos.exchange.rate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;


/**
 * Objeto de salida con el calculo de tipo de cambio
 */
@AllArgsConstructor
@Data
public class ExchangeRateResponse implements Serializable {

    private Double monto;
    private Double montoConvertido;
    private String monedaOrigen;
    private String monedaDestino;
    private Double valorTipoCambio;

}
