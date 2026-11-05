
package com.teste.boot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.teste.boot.service.TarifaService;

/**
 * Valida as tarifas cadastradas e as combinações de DDD não atendidas.
 */
class TarifaServiceTests {

    private final TarifaService service = new TarifaService();

    @Test
    void deveRetornarTarifaCorretaParaTodasAsCombinacoesAtendidas() {
        assertEquals(new BigDecimal("1.90"), service.buscarTarifa("011", "016"));
        assertEquals(new BigDecimal("1.70"), service.buscarTarifa("011", "017"));
        assertEquals(new BigDecimal("2.90"), service.buscarTarifa("016", "011"));
        assertEquals(new BigDecimal("2.70"), service.buscarTarifa("017", "011"));
        assertEquals(new BigDecimal("0.90"), service.buscarTarifa("011", "018"));
        assertEquals(new BigDecimal("1.90"), service.buscarTarifa("018", "011"));
    }

    @Test
    void deveLancarExcecaoParaCombinacaoNaoAtendida() {
        assertThrows(
                IllegalArgumentException.class,
                () -> service.buscarTarifa("019", "011")
        );
    }
}
