
package com.teste.boot.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.teste.boot.exception.DadosChamadaInvalidosException;
import com.teste.boot.model.Chamada;

/**
 * Coordena a validação dos dados, consulta de tarifas
 * e cálculo dos valores de uma chamada.
 */
@Service
public class CalcularChamadaService {

	private final TarifaService tarifaService;

	public CalcularChamadaService(TarifaService tarifaService) {
		this.tarifaService = tarifaService;
	}

	/**
	 * Valida os dados e calcula os valores com e sem o plano FaleMais.
	 */
	public Chamada calcular(Chamada chamada) {

		validarDados(chamada);

		BigDecimal tarifa = tarifaService.buscarTarifa(
				chamada.getCodigo_origem(),
				chamada.getCodigo_destino()
		);

		chamada.setTarifa(tarifa);

		calcularValorComPlano(chamada);
		chamada.tarifaNormal();

		return chamada;
	}

	/**
	 * Protege as regras de negócio independentemente da origem da chamada.
	 */
	private void validarDados(Chamada chamada) {

		if (chamada == null) {
			throw new DadosChamadaInvalidosException(
					"Os dados da chamada são obrigatórios."
			);
		}

		if (chamada.getCodigo_origem() == null
				|| chamada.getCodigo_origem().trim().isEmpty()) {
			throw new DadosChamadaInvalidosException(
					"O DDD de origem é obrigatório."
			);
		}

		if (chamada.getCodigo_destino() == null
				|| chamada.getCodigo_destino().trim().isEmpty()) {
			throw new DadosChamadaInvalidosException(
					"O DDD de destino é obrigatório."
			);
		}

		if (chamada.getTempo() == null || chamada.getTempo() <= 0) {
			throw new DadosChamadaInvalidosException(
					"A duração da chamada deve ser maior que zero."
			);
		}

		if (chamada.getTempo() > 1440) {
			throw new DadosChamadaInvalidosException(
					"A duração máxima permitida é de 1440 minutos."
			);
		}

		if (chamada.getTipo_plano() == null
				|| chamada.getTipo_plano().trim().isEmpty()) {
			throw new DadosChamadaInvalidosException(
					"O plano FaleMais é obrigatório."
			);
		}
	}

	/**
	 * Executa o cálculo de acordo com o plano selecionado.
	 */
	private void calcularValorComPlano(Chamada chamada) {

		switch (chamada.getTipo_plano()) {
			case "FaleMais30":
				chamada.faleMais30();
				break;

			case "FaleMais60":
				chamada.faleMais60();
				break;

			case "FaleMais120":
				chamada.faleMais120();
				break;

			default:
				throw new DadosChamadaInvalidosException(
						"Plano FaleMais não reconhecido: "
								+ chamada.getTipo_plano()
				);
		}
	}
}
