package com.taskmanager.taskmanager.exception;

import com.taskmanager.taskmanager.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionFilter {

    @ExceptionHandler(OrganizationException.class)
    public ResponseEntity<ErrorResponse> handleOrganizationServiceException(OrganizationException e) {
        ErrorResponse message = ErrorResponse
                .builder()
                .status(e.getCode())
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(message, e.getCode());
    }


    @ExceptionHandler(AuthenticationException.class)
    private ResponseEntity<ErrorResponse> handleExceptionAuthenticationException(AuthenticationException ex) {
        ErrorResponse message = ErrorResponse
                .builder()
                .status(HttpStatus.UNAUTHORIZED)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        if (ex instanceof AuthenticationException) {
            return this.handleExceptionAuthenticationException((AuthenticationException) ex);
        }
        ErrorResponse message = ErrorResponse
                .builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
