
package com.teste.boot.exception;

/**
 * Representa uma inconsistência nos dados utilizados
 * durante o processamento de uma chamada.
 */
public class DadosChamadaInvalidosException extends RuntimeException {

    public DadosChamadaInvalidosException(String mensagem) {
        super(mensagem);
    }
}
