
package com.teste.boot.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

/**
 * Centraliza a consulta das tarifas por combinação de DDD.
 */
@Service
public class TarifaService {

    public BigDecimal buscarTarifa(String codigoOrigem, String codigoDestino) {

        if ("011".equals(codigoOrigem) && "016".equals(codigoDestino)) {
            return new BigDecimal("1.90");
        }

        if ("011".equals(codigoOrigem) && "017".equals(codigoDestino)) {
            return new BigDecimal("1.70");
        }

        if ("016".equals(codigoOrigem) && "011".equals(codigoDestino)) {
            return new BigDecimal("2.90");
        }

        if ("017".equals(codigoOrigem) && "011".equals(codigoDestino)) {
            return new BigDecimal("2.70");
        }

        if ("011".equals(codigoOrigem) && "018".equals(codigoDestino)) {
            return new BigDecimal("0.90");
        }

        if ("018".equals(codigoOrigem) && "011".equals(codigoDestino)) {
            return new BigDecimal("1.90");
        }

        throw new IllegalArgumentException(
                "Combinação de DDD não atendida: "
                        + codigoOrigem + " para " + codigoDestino
        );
    }
}
