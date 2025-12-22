package com.sai.inventory.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private int errorCode = -1;

    public ValidationException() {
        super();
    }

    public ValidationException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
