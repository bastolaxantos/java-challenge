package com.scopic.javachallenge.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(InsufficientNumberOfPlayersException.class)
  public ResponseEntity<Object> handleResourceNotFoundException(
      InsufficientNumberOfPlayersException ex) {

    Map<String, String> details = new HashMap<>();
    details.put("message", ex.getMessage());

    return ResponseEntity.ok(details);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Object> handleIllegalArgumentException(
      IllegalArgumentException ex) {
    return ResponseEntity.ok(ex.getMessage());
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    Map<String, String> errors = new HashMap<>();
    FieldError fieldError = (FieldError) ex.getBindingResult()
        .getAllErrors().get(0);
    String fieldName = fieldError.getField();
    String value = (String) fieldError.getRejectedValue();
    errors.put("message", "Invalid value for field " + fieldName + ": " + value);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }
}
