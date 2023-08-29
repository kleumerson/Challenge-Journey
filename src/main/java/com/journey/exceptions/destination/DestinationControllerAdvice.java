package com.journey.exceptions.destination;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@ControllerAdvice(basePackages = "com.journey.controller")
public class DestinationControllerAdvice {
    @ResponseBody
    @ExceptionHandler(DestinationNotFoundException.class)
    public ResponseEntity<DestinationExceptionHandler> destinationNotFound(DestinationNotFoundException destinationNotFound) {
        DestinationExceptionHandler error = new DestinationExceptionHandler(
                new Date(), HttpStatus.NOT_FOUND.value(), "Destination not found.");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DestinationExceptionHandler> MethodArgumentNotValidException(MethodArgumentNotValidException exception){

        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
            sb.append("The field " );
            sb.append(fieldError.getDefaultMessage());
        }

        DestinationExceptionHandler error = new DestinationExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(), sb.toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
