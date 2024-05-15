package com.example.cats.handler;

import com.example.cats.exceptions.BadRequestException;
import com.example.cats.exceptions.BadRequestExceptionDetails;
import com.example.cats.exceptions.ValidationExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// sempre que esperar uma exceção vai utilizar essa classe
// com essa anotacao falo para o controller que essa é uma classe com as informações que ele precisa utilizar
@ControllerAdvice
public class RestExceptionHandler {
    // falando para os controllers que caso vc tenha uma exceção do tipo badrequest, utiliza-se esse exceptionhandler
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException bre) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, Check the Documentation")
                        .details(bre.getMessage())
                        .developerMessage(bre.getClass().getName())
                .build(), HttpStatus.BAD_REQUEST
        );
    }

    // descobrindo qual a exeção que tá sendo lançada
    // com esse handler, a requisição do post vai ficar com todas as exceções customizadas
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String fieldsMassage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Exception, Invalid Fields")
                        .details("Check the field(s) error")
                        .developerMessage(exception.getClass().getName())
                        .fields(fields)
                        .fieldMessage(fieldsMassage)
                .build(), HttpStatus.BAD_REQUEST
        );
    }

}
