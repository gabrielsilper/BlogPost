package com.github.gabrielsilper.BlogPost.utils;

import jakarta.validation.ConstraintViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

public class ErrorUtils {
    public static String getConstraintViolationMessages(ConstraintViolationException e) {
        List<String> constraintViolationMessages = e.getConstraintViolations()
                .stream()
                .map(constraintViolation -> constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage())
                .toList();

        return String.join(", ", constraintViolationMessages);
    }

    public static String getMethodArgumentNotValidMessages(MethodArgumentNotValidException e) {
        List<String> errorMessages = e.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + " " + error.getDefaultMessage())
                .toList();

        return String.join(", ", errorMessages);
    }
}
