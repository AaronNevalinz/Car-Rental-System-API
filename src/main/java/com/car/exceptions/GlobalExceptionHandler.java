package com.car.exceptions;

import com.car.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/*******************************************************************
    This class is a global exception handler, specifically handling validation errors that
    occur when processing requests
 ***********************************/

@RestControllerAdvice
//this annotation makes this class handle exceptions globally across all controllers
//any method inside this class marked with @ExceptionHandler will catch specific exceptions and return
//a custom response
public class GlobalExceptionHandler {

//    This ensures that the API returns a 400 Bad Request status when validation fails
    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    This method catches validation errors triggered by @valid in request bodies
    @ExceptionHandler(MethodArgumentNotValidException.class)
//   MethodArgumentNotValidException ex exception is thrown when request body validation fails
    public ApiResponse<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
//        this map errors will store field names as keys and error messages as values
        Map<String, String> errors = new HashMap<>();

//        Collect field errors
//        [ex.getBindingResult().getFieldErrors()] -> returns a list of validation errors
//        [error.getField()] → Gets the field name (e.g., "brand", "year").
//        [error.getDefaultMessage()] → Gets the validation error message.
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        if(!errors.isEmpty()){
            return ApiResponse.error("Validation failed", errors);
        }
        return ApiResponse.error("Validation error", null);
    }
}
