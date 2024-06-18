package com.taskmanager.taskmanager.exception.authentication;

import org.springframework.security.core.AuthenticationException;

public class LoginException extends AuthenticationException {
    public LoginException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public LoginException(String msg) {
        super(msg);
    }
}
