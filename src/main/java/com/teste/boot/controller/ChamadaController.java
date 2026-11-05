
package com.teste.boot.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teste.boot.model.Chamada;
import com.teste.boot.service.CalcularChamadaService;

/**
 * Controla as operações de cálculo e consulta dos planos de chamadas.
 */
@Controller
@RequestMapping("/chamadas")
public class ChamadaController {

	private final CalcularChamadaService service;

	public ChamadaController(CalcularChamadaService service) {
		this.service = service;
	}

	@GetMapping("/calculo")
	public String cadastrar(Model model) {
		model.addAttribute("chamada", new Chamada());
		return "/chamadas/calculo";
	}

	@GetMapping("/lista")
	public String listar() {
		return "/chamadas/lista";
	}

	@PostMapping("/calcular")
	public String calcular(
			@Valid @ModelAttribute("chamada") Chamada chamada,
			BindingResult resultadoValidacao,
			Model model) {

		if (resultadoValidacao.hasErrors()) {
			return "/chamadas/calculo";
		}

		Chamada resultado = service.calcular(chamada);

		model.addAttribute("resultado", resultado);

		return "/chamadas/calculo";
	}
}
