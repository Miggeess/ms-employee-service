package com.babel.employeeservice.interfaces.handler;

import com.babel.employeeservice.domain.exception.InvalidDateFormatException;
import com.babel.employeeservice.interfaces.error.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request){

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        ApiError apiError = new ApiError(message, request.getRequestURI(), HttpStatus.BAD_REQUEST.value(), Instant.now());

        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleMalformedJson(HttpMessageNotReadableException ex, HttpServletRequest request){

        String message = "Malformed JSOM request";
        ApiError apiError = new ApiError(message, request.getRequestURI(), HttpStatus.BAD_REQUEST.value(), Instant.now());

        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiError> handleMissingParameter(MissingServletRequestParameterException ex, HttpServletRequest request){

        String message = "Missing required parameter: " + ex.getParameterName();
        ApiError apiError = new ApiError(message, request.getRequestURI(), HttpStatus.BAD_REQUEST.value(), Instant.now());

        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(InvalidDateFormatException.class)
    public ResponseEntity<ApiError> handleInvalidDateFormat(InvalidDateFormatException ex, HttpServletRequest request){

        ApiError apiError = new ApiError(ex.getMessage(), request.getRequestURI(), HttpStatus.BAD_REQUEST.value(), Instant.now());

        return ResponseEntity.badRequest().body(apiError);
    }
}
