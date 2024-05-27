package com.taskmanager.taskmanager.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
public class OrganizationException  extends RuntimeException {
    HttpStatus code;
    public OrganizationException(String message, HttpStatus code) {
        super(message);
        this.code = code;
    }
}
