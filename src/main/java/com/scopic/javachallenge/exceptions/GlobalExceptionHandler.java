package com.scopic.javachallenge.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InsufficientNumberOfPlayersException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(
        InsufficientNumberOfPlayersException ex) {

        return createErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(
        IllegalArgumentException ex) {

        return createErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(
        ConstraintViolationException ex) {
        ConstraintViolation<?> violation = ex.getConstraintViolations().iterator().next();
        return createErrorResponse("Invalid value for field " + violation.getPropertyPath() + ": " + violation.getInvalidValue());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request) {
        FieldError fieldError = (FieldError) ex.getBindingResult()
            .getAllErrors().get(0);
        String fieldName = fieldError.getField();
        String value = (String) fieldError.getRejectedValue();

        return createErrorResponse("Invalid value for field " + fieldName + ": " + value);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(
        Exception ex
    ) {
        return createErrorResponse(ex.getMessage());
    }

    private ResponseEntity<Object> createErrorResponse(String message) {
        Map<String, String> details = new HashMap<>();
        details.put("message", message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(details);
    }
}
