package com.github.gabrielsilper.BlogPost.controllers;

import com.github.gabrielsilper.BlogPost.exceptions.UserNotFoundException;
import com.github.gabrielsilper.BlogPost.models.dtos.ErrorMessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageResponse> generalExceptionHandler(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMessageResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }
}
