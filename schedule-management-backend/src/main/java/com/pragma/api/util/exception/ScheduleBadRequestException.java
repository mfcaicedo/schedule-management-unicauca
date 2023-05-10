package com.pragma.api.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ScheduleBadRequestException extends CoreException{

    public ScheduleBadRequestException(String key, String arg) {
        super(key, arg);
    }

    public ScheduleBadRequestException(String developerMessage, String userMessage, int status, Throwable e) {
        super(developerMessage, userMessage, status, e);
    }

    public ScheduleBadRequestException(String developerMessage, String userMessage, int status) {
        super(developerMessage, userMessage, status);
    }

    public ScheduleBadRequestException(String userMessage, int status, Throwable e) {
        super(userMessage, status, e);
    }
}
