
package com.teste.boot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.teste.boot.service.TarifaService;

/**
 * Valida as tarifas cadastradas e as combinações de DDD não atendidas.
 */
class TarifaServiceTests {

    private final TarifaService service = new TarifaService();

    @Test
    void deveRetornarTarifaCorretaParaTodasAsCombinacoesAtendidas() {
        assertEquals(1.90, service.buscarTarifa("011", "016"), 0.001);
        assertEquals(1.70, service.buscarTarifa("011", "017"), 0.001);
        assertEquals(2.90, service.buscarTarifa("016", "011"), 0.001);
        assertEquals(2.70, service.buscarTarifa("017", "011"), 0.001);
        assertEquals(0.90, service.buscarTarifa("011", "018"), 0.001);
        assertEquals(1.90, service.buscarTarifa("018", "011"), 0.001);
    }

    @Test
    void deveLancarExcecaoParaCombinacaoNaoAtendida() {
        assertThrows(
                IllegalArgumentException.class,
                () -> service.buscarTarifa("019", "011")
        );
    }
}
