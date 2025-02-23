package com.github.gabrielsilper.BlogPost.controllers;

import com.github.gabrielsilper.BlogPost.exceptions.EmailAlreadyExistsException;
import com.github.gabrielsilper.BlogPost.exceptions.UserNotFoundException;
import com.github.gabrielsilper.BlogPost.exceptions.UsernameAlreadyExistsException;
import com.github.gabrielsilper.BlogPost.models.dtos.ErrorMessageResponse;
import com.github.gabrielsilper.BlogPost.utils.ErrorUtils;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler({
            UserNotFoundException.class
    })
    public ResponseEntity<ErrorMessageResponse> notFoundExceptionHandler(Exception e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessageResponse(e.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler({
            UsernameAlreadyExistsException.class,
            EmailAlreadyExistsException.class
    })
    public ResponseEntity<ErrorMessageResponse> AlreadyExistsExceptionHandler(Exception e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorMessageResponse(e.getMessage(), HttpStatus.CONFLICT.value()));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String errorMessages = ErrorUtils.getMethodArgumentNotValidMessages(e);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessageResponse(errorMessages, HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessageResponse> constraintViolationExceptionHandler(ConstraintViolationException e) {
        String constraintViolationMessages = ErrorUtils.getConstraintViolationMessages(e);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessageResponse(constraintViolationMessages, HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageResponse> generalExceptionHandler(Exception e) {
        System.out.println(e.getClass().getName()); // Remover se for publicado

        // Logar a exceção e retornar uma mensagem genérica.
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMessageResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }
}
