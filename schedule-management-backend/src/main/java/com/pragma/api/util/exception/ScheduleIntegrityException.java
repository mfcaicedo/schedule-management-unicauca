package com.pragma.api.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ScheduleIntegrityException extends CoreException{

    public ScheduleIntegrityException(String key, String arg) {
        super(key, arg);
    }

    public ScheduleIntegrityException(String developerMessage, String userMessage, int status, Throwable e) {
        super(developerMessage, userMessage, status, e);
    }

    public ScheduleIntegrityException(String developerMessage, String userMessage, int status) {
        super(developerMessage, userMessage, status);
    }

    public ScheduleIntegrityException(String userMessage, int status, Throwable e) {
        super(userMessage, status, e);
    }
}
