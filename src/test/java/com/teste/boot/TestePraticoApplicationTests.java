
package com.teste.boot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.teste.boot.model.Chamada;
import com.teste.boot.service.CalcularChamadaService;

class TestePraticoApplicationTests {

	private final CalcularChamadaService service =
			new CalcularChamadaService();

	/*
	 * Testes apenas dos métodos que fazem a manipulação de valores que são de extrema importância
	 */

	@Test
	void calcularTestFaleMais30() {
		Chamada objeto = new Chamada();
		objeto.setNome("Felipe");
		objeto.setCodigo_origem("011");
		objeto.setCodigo_destino("016");
		objeto.setTipo_plano("FaleMais30");
		objeto.setTempo(20);

		service.calcular(objeto);

		assertEquals("0,00", objeto.getValor_plano());
		assertEquals("38,00", objeto.getValor_normal());
	}

	@Test
	void calcularTestFaleMais60() {
		Chamada objeto = new Chamada();
		objeto.setNome("Felipe");
		objeto.setCodigo_origem("011");
		objeto.setCodigo_destino("017");
		objeto.setTipo_plano("FaleMais60");
		objeto.setTempo(80);

		service.calcular(objeto);

		assertEquals("37,40", objeto.getValor_plano());
		assertEquals("136,00", objeto.getValor_normal());
	}

	@Test
	void calcularTestFaleMais120() {
		Chamada objeto = new Chamada();
		objeto.setNome("Felipe");
		objeto.setCodigo_origem("018");
		objeto.setCodigo_destino("011");
		objeto.setTipo_plano("FaleMais120");
		objeto.setTempo(200);

		service.calcular(objeto);

		assertEquals("167,20", objeto.getValor_plano());
		assertEquals("380,00", objeto.getValor_normal());
	}
}
