package com.datos.exchange.rate.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Manejador global de excepciones para la aplicación.
 */
@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    /**
     * Maneja las excepciones lanzadas por CurrencyConverterUtil.
     *
     * @param ex La excepción capturada.
     * @return Una respuesta JSON con detalles del error.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleCurrencyConversionException(Exception ex) {
        log.error("Ha ocurrido un error al realizar la conversion");
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("message", ex.getMessage());
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("error", "Currency conversion failed");

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

