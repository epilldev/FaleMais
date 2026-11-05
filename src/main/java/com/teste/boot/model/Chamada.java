
package com.teste.boot.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Representa os dados de entrada e os resultados financeiros de uma chamada.
 * Centraliza as operações de cálculo dos valores dos planos FaleMais.
 */
public class Chamada {

	@NotBlank(message = "O DDD de origem é obrigatório.")
	@Pattern(regexp = "\\d{3}", message = "O DDD de origem deve possuir três dígitos.")
	private String codigo_origem;

	@NotBlank(message = "O DDD de destino é obrigatório.")
	@Pattern(regexp = "\\d{3}", message = "O DDD de destino deve possuir três dígitos.")
	private String codigo_destino;

	private String nome;

	@NotNull(message = "A duração da chamada é obrigatória.")
	@Min(value = 1, message = "A duração deve ser de pelo menos um minuto.")
	@Max(value = 1440, message = "A duração máxima permitida é de 1440 minutos.")
	private Integer tempo;

	private BigDecimal tarifa = BigDecimal.ZERO;

	private String valor_plano = "0,00";
	private String valor_normal = "0,00";

	@NotBlank(message = "Selecione um plano FaleMais.")
	private String tipo_plano;

	private final DecimalFormat df = criarFormatadorMonetario();

	private DecimalFormat criarFormatadorMonetario() {
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols(
				new Locale("pt", "BR")
		);

		DecimalFormat formatador = new DecimalFormat("#,##0.00", simbolos);
		formatador.setRoundingMode(RoundingMode.HALF_UP);

		return formatador;
	}

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

	public Integer getTempo() {
		return tempo;
	}

	public void setTempo(Integer tempo) {
		this.tempo = tempo;
	}

	public BigDecimal getTarifa() {
		return tarifa;
	}

	public void setTarifa(BigDecimal tarifa) {
		this.tarifa = tarifa != null ? tarifa : BigDecimal.ZERO;
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

	public void faleMais30() {
		calcularValorPlano(30);
	}

	public void faleMais60() {
		calcularValorPlano(60);
	}

	public void faleMais120() {
		calcularValorPlano(120);
	}

	/**
	 * Calcula o valor da chamada considerando os minutos gratuitos do plano.
	 */
	private void calcularValorPlano(int minutosGratuitos) {
		int minutosExcedentes = Math.max(0, this.tempo - minutosGratuitos);

		BigDecimal tarifaComAcrescimo = this.tarifa.multiply(
				new BigDecimal("1.10")
		);

		BigDecimal valor = tarifaComAcrescimo.multiply(
				BigDecimal.valueOf(minutosExcedentes)
		);

		this.valor_plano = df.format(valor);
	}

	/**
	 * Calcula o valor da chamada sem aplicar os minutos gratuitos.
	 */
	public void tarifaNormal() {
		BigDecimal valor = this.tarifa.multiply(
				BigDecimal.valueOf(this.tempo)
		);

		this.valor_normal = df.format(valor);
	}

	public enum TipoPlano {
		FaleMais30,
		FaleMais60,
		FaleMais120
	}
}
