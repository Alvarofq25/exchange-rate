package com.datos.exchange.rate;


import com.datos.exchange.rate.service.impl.ExternalCurrencyServiceImpl;
import com.datos.exchange.rate.util.CurrencyConverterUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ExternalCurrencyServiceImplTest {

    @InjectMocks
    private ExternalCurrencyServiceImpl externalCurrencyService;

    @Mock
    private CurrencyConverterUtil currencyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetExchangeRate_Success() throws Exception {
        String monedaOrigen = "USD";
        String monedaDestino = "EUR";
        double expectedRate = 0.85;
        when(currencyService.calcularTipoDeCambio(monedaOrigen, monedaDestino)).thenReturn(expectedRate);
        Mono<Double> resultMono = externalCurrencyService.getExchangeRate(monedaOrigen, monedaDestino);
        resultMono.subscribe(result -> assertEquals(expectedRate, result));
    }


}