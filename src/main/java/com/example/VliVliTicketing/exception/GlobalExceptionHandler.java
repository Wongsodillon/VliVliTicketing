package com.example.VliVliTicketing.exception;

import com.example.VliVliTicketing.response.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

//  @ExceptionHandler(WebExchangeBindException.class)
//  public Mono<ResponseEntity<CustomErrorResponse>> handleValidationException(WebExchangeBindException ex) {
//    Map<String, String> errors = new HashMap<>();
//    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//      errors.put(error.getField(), error.getDefaultMessage());
//    }
//
//    CustomErrorResponse response = new CustomErrorResponse(
//        HttpStatus.BAD_REQUEST.value(),
//        HttpStatus.BAD_REQUEST.getReasonPhrase(),
//        "Validation Failed",
//        errors
//    );
//
//    return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response));
//  }
//
//  @ExceptionHandler(ServerWebInputException.class)
//  public Mono<ResponseEntity<CustomErrorResponse>> handleEnumException(ServerWebInputException  ex) {
//    CustomErrorResponse errorResponse = new CustomErrorResponse(
//        HttpStatus.BAD_REQUEST.value(),
//        HttpStatus.BAD_REQUEST.getReasonPhrase(),
//        "Validation Failed",
//        Map.of("error", ex.getMessage())
//    );
//
//    return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse));
//  }
//
//  @ExceptionHandler(Exception.class)
//  public Mono<ResponseEntity<CustomErrorResponse>> handleException(Exception ex) {
//    CustomErrorResponse errorResponse = new CustomErrorResponse(
//        HttpStatus.INTERNAL_SERVER_ERROR.value(),
//        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
//        "An unexpected error occurred",
//        Map.of("error", ex.getMessage())
//    );
//
//    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse));
//  }
}
