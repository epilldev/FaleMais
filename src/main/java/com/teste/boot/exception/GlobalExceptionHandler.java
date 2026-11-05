
package com.teste.boot.exception;

import java.util.stream.Collectors;

import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.support.WebExchangeBindException;

import com.teste.boot.model.Chamada;

/**
 * Centraliza o tratamento de exceções da aplicação e apresenta
 * mensagens adequadas nas páginas do Thymeleaf.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Trata inconsistências identificadas durante o processamento.
     */
    @ExceptionHandler(DadosChamadaInvalidosException.class)
    public String tratarDadosInvalidos(
            DadosChamadaInvalidosException exception,
            Model model) {

        model.addAttribute("mensagemErro", exception.getMessage());
        model.addAttribute("chamada", new Chamada());

        return "/chamadas/calculo";
    }

    /**
     * Trata combinações de DDD sem tarifa cadastrada.
     */
    @ExceptionHandler(CombinacaoDddNaoAtendidaException.class)
    public String tratarCombinacaoNaoAtendida(
            CombinacaoDddNaoAtendidaException exception,
            Model model) {

        model.addAttribute("mensagemErro", exception.getMessage());
        model.addAttribute("chamada", new Chamada());

        return "/chamadas/calculo";
    }

    /**
     * Trata erros inesperados sem expor detalhes internos.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String tratarErroInterno(Model model) {

        model.addAttribute(
                "mensagemErro",
                "Ocorreu um erro inesperado. Tente novamente."
        );

        model.addAttribute("chamada", new Chamada());

        return "/chamadas/calculo";
    }
}
