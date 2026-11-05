
package com.teste.boot.service;

import org.springframework.stereotype.Service;

import com.teste.boot.model.Chamada;

import java.math.BigDecimal;

/**
 * Coordena a consulta de tarifas e o cálculo dos valores das chamadas.
 */
@Service
public class CalcularChamadaService {

	private final TarifaService tarifaService;

	public CalcularChamadaService(TarifaService tarifaService) {
		this.tarifaService = tarifaService;
	}

	public Chamada calcular(Chamada chamadaModel) {

		BigDecimal tarifa = tarifaService.buscarTarifa(
				chamadaModel.getCodigo_origem(),
				chamadaModel.getCodigo_destino()
		);

		chamadaModel.setTarifa(tarifa);

		if (chamadaModel.getTipo_plano().equals(Chamada.TipoPlano.FaleMais30.toString())) {
			chamadaModel.faleMais30(chamadaModel);
		} else if (chamadaModel.getTipo_plano().equals(Chamada.TipoPlano.FaleMais60.toString())) {
			chamadaModel.faleMais60(chamadaModel);
		} else if (chamadaModel.getTipo_plano().equals(Chamada.TipoPlano.FaleMais120.toString())) {
			chamadaModel.faleMais120(chamadaModel);
		}

		chamadaModel.tarifaNormal(chamadaModel);

		return chamadaModel;
	}
}
