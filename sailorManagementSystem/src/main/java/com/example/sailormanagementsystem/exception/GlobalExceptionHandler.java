package com.example.sailormanagementsystem.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception e) {
        System.out.println(e.getClass().getSimpleName());

        ApiError apiError = new ApiError(
                "Internal Server Error",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ZonedDateTime.now(),
                List.of("Error occurred")
        );

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiError> handleNoSuchElementException(NoSuchElementException e) {
        var errors = List.of(e.getMessage());

        ApiError apiError = new ApiError(
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                ZonedDateTime.now(),
                errors
        );

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SameValueViolation.class)
    public ResponseEntity<ApiError> handleSameValueViolation(SameValueViolation e) {
        List<String> errors = List.of(e.getMessage());

        ApiError apiError = new ApiError(
                e.getMessage(),
                HttpStatus.CONFLICT.value(),
                ZonedDateTime.now(),
                errors
        );

        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errors = e.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        ApiError apiError = new ApiError(
                "Illegal argument provided",
                HttpStatus.BAD_REQUEST.value(),
                ZonedDateTime.now(),
                errors
        );

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ApiError> handleMethodValidationException(HandlerMethodValidationException e) {
        var errors = e.getAllErrors().stream()
                .map(MessageSourceResolvable::getDefaultMessage)
                .toList();

        ApiError apiError = new ApiError(
                "ConstraintViolationException",
                HttpStatus.BAD_REQUEST.value(),
                ZonedDateTime.now(),
                errors
        );

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolationException(ConstraintViolationException ex) {
        var errors = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .toList();

        ApiError apiError = new ApiError(
                "ConstraintViolationException",
                HttpStatus.BAD_REQUEST.value(),
                ZonedDateTime.now(),
                errors
        );

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        ApiError apiError = new ApiError(
                "Illegal duplicated value found",
                HttpStatus.CONFLICT.value(),
                ZonedDateTime.now(),
                List.of(e.getMessage())
        );

        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SameEmailException.class)
    public ResponseEntity<ApiError> handleSameEmailException(SameEmailException e) {
        List<String> errors = List.of(e.getMessage());

        ApiError apiError = new ApiError(
                e.getMessage(),
                HttpStatus.CONFLICT.value(),
                ZonedDateTime.now(),
                errors
        );

        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }
}
