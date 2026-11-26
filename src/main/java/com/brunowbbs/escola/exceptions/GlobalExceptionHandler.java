package com.brunowbbs.escola.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;


// Marca a classe como um interceptador global de exceções para controllers REST
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Intercepta qualquer MethodArgumentNotValidException lançada quando o @Valid falha
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<com.brunowbbs.escola.exceptions.ErroCampoDTO>> tratarValidacao(MethodArgumentNotValidException ex) {
        // Pega o resultado da validação e extrai a lista de erros de cada campo
        List<com.brunowbbs.escola.exceptions.ErroCampoDTO> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                // Converte cada FieldError no DTO simples com "campo" e "mensagem"
                .map(erro -> new com.brunowbbs.escola.exceptions.ErroCampoDTO(erro.getField(), erro.getDefaultMessage()))
                // Gera a lista final imutável com todos os erros encontrados
                .toList();

        // Devolve HTTP 400 (Bad Request) com a lista de erros no corpo da resposta
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }

    @ExceptionHandler(AlunoNaoEncontradoException.class)
    public ResponseEntity<?> tratarNaoEncontrado(AlunoNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("mensagem", ex.getMessage()));
    }

}
