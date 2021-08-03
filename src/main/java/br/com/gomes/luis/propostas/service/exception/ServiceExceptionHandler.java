package br.com.gomes.luis.propostas.service.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> validation(DataIntegrityViolationException e, HttpServletRequest request) {

        List<String> msgs = new ArrayList<>();

        msgs.add("Esse documento já está cadastrado no banco de dados!");

        StandardError err = new StandardError(HttpStatus.CONFLICT.value(), msgs, System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {

        List<String> msgs = new ArrayList<>();

        for (FieldError x : e.getBindingResult().getFieldErrors()) {
            msgs.add(x.getDefaultMessage());

        }

        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), msgs, System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> validation(IllegalArgumentException e, HttpServletRequest request) {

        List<String> msgs = new ArrayList<>();
        msgs.add(e.getLocalizedMessage());
        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), msgs, System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<StandardError> validation(EmptyResultDataAccessException e, HttpServletRequest request) {

        List<String> msgs = new ArrayList<>();
        msgs.add("Não existe essa informação no banco de dados para ser excluido");
        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), msgs, System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

}
