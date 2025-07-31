package com.tiba.center.exception;

import com.tiba.center.common.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiResponse<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            error -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });
    return ApiResponse.error(errors, "Validation errors detected");
  }

  @ExceptionHandler(DuplicateResourceException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ApiResponse<Object> handleDuplicateResourceException(DuplicateResourceException ex) {
    return ApiResponse.error(ex.getMessage(), "DATA_CONFLICT");
  }

  @ExceptionHandler(NotFoundException.class)
  public ApiResponse<Object> notFoundException(NotFoundException ex) {
    return ApiResponse.error(ex.getMessage(), "NOT_FOUND");
  }

  @ExceptionHandler(InvalidActionException.class)
  public ApiResponse<Object> invalidActionException(InvalidActionException ex) {
    return ApiResponse.error(ex.getMessage(), "InvalidActionException");
  }
}
