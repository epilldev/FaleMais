
package com.teste.boot.exception;

/**
 * Representa uma tentativa de cálculo com uma combinação de DDD
 * para a qual não existe tarifa cadastrada.
 */
public class CombinacaoDddNaoAtendidaException extends RuntimeException {

    public CombinacaoDddNaoAtendidaException(String codigoOrigem, String codigoDestino) {
        super("Combinação de DDD não atendida: "
                + codigoOrigem + " para " + codigoDestino);
    }
}
