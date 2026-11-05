
package com.teste.boot.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.teste.boot.exception.CombinacaoDddNaoAtendidaException;

/**
 * Centraliza a consulta das tarifas por combinação de DDD.
 */
@Service
public class TarifaService {

    private static final Map<String, BigDecimal> TARIFAS = criarTarifas();

    private static Map<String, BigDecimal> criarTarifas() {
        Map<String, BigDecimal> tarifas = new HashMap<>();

        tarifas.put("011-016", new BigDecimal("1.90"));
        tarifas.put("011-017", new BigDecimal("1.70"));
        tarifas.put("016-011", new BigDecimal("2.90"));
        tarifas.put("017-011", new BigDecimal("2.70"));
        tarifas.put("011-018", new BigDecimal("0.90"));
        tarifas.put("018-011", new BigDecimal("1.90"));

        return Collections.unmodifiableMap(tarifas);
    }

    public BigDecimal buscarTarifa(String codigoOrigem, String codigoDestino) {
        String combinacao = codigoOrigem + "-" + codigoDestino;

        BigDecimal tarifa = TARIFAS.get(combinacao);

        if (tarifa == null) {
            throw new CombinacaoDddNaoAtendidaException(
                    codigoOrigem,
                    codigoDestino
            );
        }

        return tarifa;
    }
}
