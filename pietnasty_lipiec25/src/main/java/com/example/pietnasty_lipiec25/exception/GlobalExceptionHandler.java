/*

This class takes my custom exception "UserNotFoundException" and changes it from status error 500 to 404 so it can display my
message implemented in UserNotFoundException

 */

package com.example.pietnasty_lipiec25.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;


import java.time.ZonedDateTime;
import java.util.List;



@RestControllerAdvice
public class GlobalExceptionHandler {

    //handler for 404 status error
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handle(UserNotFoundException e) {
        List<String> errors = List.of(e.getMessage());

        ApiError apiError = new ApiError(
                "Validation Error",
                HttpStatus.NOT_FOUND.value(),
                ZonedDateTime.now(),
                errors
        );
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    //handler for status 400 error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handle(MethodArgumentNotValidException e) {

        List<String> errors = e.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        ApiError apiError = new ApiError(
                "Validation Error",
                HttpStatus.BAD_REQUEST.value(),
                ZonedDateTime.now(),
                errors
        );
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    // 409 duplication status error
    @ExceptionHandler(DuplicateEmailExcepiton.class)
    public ResponseEntity<ApiError> handleDuplicateEmail(DuplicateEmailExcepiton e) {
        List<String> errors = List.of(e.getMessage());

        ApiError apiError = new ApiError(
                "Validation Error",
                HttpStatus.CONFLICT.value(),
                ZonedDateTime.now(),
                errors
        );

        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    // method for checking what is the name of caught exception (after pushing a http request the exception name is displayed in the api console
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handle(Exception e) {
        System.out.println(e.getClass().getSimpleName());

        ApiError apiError = new ApiError(
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ZonedDateTime.now(),
                List.of(e.getMessage())
        );

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //method for handling a PropertyReferenceException ex. http request while sorting with unknown field (casted as 400 error)
    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<ApiError> handle(PropertyReferenceException e) {
        List<String> errors = List.of(e.getMessage());

        ApiError apiError = new ApiError(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                ZonedDateTime.now(),
                errors
        );

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiError> handleResponseStatusException(ResponseStatusException e) {
        ApiError apiError = new ApiError(
                e.getReason(),
                e.getStatusCode().value(),
                ZonedDateTime.now(),
                List.of(e.getMessage())
        );
        return new ResponseEntity<>(apiError, e.getStatusCode());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        ApiError apiError = new ApiError(
                "Forbidden value in id field",
                HttpStatus.FORBIDDEN.value(),
                ZonedDateTime.now(),
                List.of(e.getMessage())
        );

        return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
    }
}
