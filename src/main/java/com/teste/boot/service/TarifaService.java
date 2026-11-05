package com.teste.boot.service;

import org.springframework.stereotype.Service;

@Service
public class TarifaService {

    public double buscarTarifa(String codigoOrigem, String codigoDestino) {

        if ("011".equals(codigoOrigem) && "016".equals(codigoDestino)) {
            return 1.90;
        }

        if ("011".equals(codigoOrigem) && "017".equals(codigoDestino)) {
            return 1.70;
        }

        if ("016".equals(codigoOrigem) && "011".equals(codigoDestino)) {
            return 2.90;
        }

        if ("017".equals(codigoOrigem) && "011".equals(codigoDestino)) {
            return 2.70;
        }

        if ("011".equals(codigoOrigem) && "018".equals(codigoDestino)) {
            return 0.90;
        }

        if ("018".equals(codigoOrigem) && "011".equals(codigoDestino)) {
            return 1.90;
        }

        throw new IllegalArgumentException(
                "Combinação de DDD não atendida: "
                        + codigoOrigem + " para " + codigoDestino
        );
    }
}