package com.cosmocats.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import org.springframework.http.ProblemDetail;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {

        StringBuilder message = new StringBuilder();
        String objectName = ex.getBindingResult().getObjectName();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            message.append("Field '")
                    .append(error.getField())
                    .append("' ")
                    .append(error.getDefaultMessage())
                    .append(". ");
        }

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, message.toString());
        problemDetail.setTitle("Bad Request");
        problemDetail.setProperty("path", request.getDescription(false).replace("uri=", ""));
        problemDetail.setProperty("status", HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);
    }
}
