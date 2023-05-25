package tech.devinhouse.aviacao.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tech.devinhouse.aviacao.exception.MalaNaoDespachadaException;
import tech.devinhouse.aviacao.exception.PassageiroMenorDeIdadeException;
import tech.devinhouse.aviacao.exception.RegistroEncontradoException;
import tech.devinhouse.aviacao.exception.RegistroNaoEncontradoException;

import tech.devinhouse.aviacao.dto.ErroResponse;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public ResponseEntity<Object> handleRegistroNaoEncontradoException(RegistroNaoEncontradoException e) {
        ErroResponse erro = new ErroResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(RegistroEncontradoException.class)
    public ResponseEntity<Object> handleRegistroEncontradoException(RegistroEncontradoException e) {
        ErroResponse erro = new ErroResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(MalaNaoDespachadaException.class)
    public ResponseEntity<Object> handleMalaNaoDespachadaException(MalaNaoDespachadaException e) {
        ErroResponse erro = new ErroResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(PassageiroMenorDeIdadeException.class)
    public ResponseEntity<Object> handlePassageiroMenorDeIdadeException(PassageiroMenorDeIdadeException e) {
        ErroResponse erro = new ErroResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }


}
