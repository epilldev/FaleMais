
package com.teste.boot.model;

import java.text.DecimalFormat;
import java.math.BigDecimal;

/**
 * Representa os dados e os resultados financeiros de uma chamada.
 */
public class Chamada {

	protected String codigo_origem;
	protected String codigo_destino;
	private String nome;
	protected int tempo = 0;
	protected BigDecimal tarifa = BigDecimal.ZERO;
	protected String valor_plano = "0,00";
	protected String valor_normal = "0,00";
	private String tipo_plano;

	private final DecimalFormat df = new DecimalFormat("#,##0.00");

	public String getCodigo_origem() {
		return codigo_origem;
	}

	public void setCodigo_origem(String codigo_origem) {
		this.codigo_origem = codigo_origem;
	}

	public String getCodigo_destino() {
		return codigo_destino;
	}

	public void setCodigo_destino(String codigo_destino) {
		this.codigo_destino = codigo_destino;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	public BigDecimal getTarifa() {
		return tarifa;
	}

	public void setTarifa(BigDecimal tarifa) {
		this.tarifa = tarifa;
	}

	public String getValor_plano() {
		return valor_plano;
	}

	public void setValor_plano(String valor_plano) {
		this.valor_plano = valor_plano;
	}

	public String getValor_normal() {
		return valor_normal;
	}

	public void setValor_normal(String valor_normal) {
		this.valor_normal = valor_normal;
	}

	public String getTipo_plano() {
		return tipo_plano;
	}

	public void setTipo_plano(String tipo_plano) {
		this.tipo_plano = tipo_plano;
	}

	public Chamada faleMais30(Chamada object) {
		calcularValorPlano(object, 30);
		return object;
	}

	public Chamada faleMais60(Chamada object) {
		calcularValorPlano(object, 60);
		return object;
	}

	public Chamada faleMais120(Chamada object) {
		calcularValorPlano(object, 120);
		return object;
	}
	/**
	 * Calcula o valor da chamada considerando os minutos gratuitos do plano.
	 */
	private void calcularValorPlano(Chamada chamada, int minutosGratuitos) {

		int minutosExcedentes = Math.max(
				0,
				chamada.tempo - minutosGratuitos
		);

		BigDecimal tarifaComAcrescimo = chamada.tarifa.multiply(
				new BigDecimal("1.10")
		);

		BigDecimal valor = tarifaComAcrescimo.multiply(
				BigDecimal.valueOf(minutosExcedentes)
		);

		chamada.valor_plano = df.format(valor);
	}

	/**
	 * Calcula o valor da chamada sem aplicar os minutos gratuitos.
	 */
	public Chamada tarifaNormal(Chamada chamada) {

		BigDecimal valor = chamada.tarifa.multiply(
				BigDecimal.valueOf(chamada.tempo)
		);

		chamada.valor_normal = df.format(valor);

		return chamada;
	}

	public enum TipoPlano {
		FaleMais30,
		FaleMais60,
		FaleMais120
	}
}
