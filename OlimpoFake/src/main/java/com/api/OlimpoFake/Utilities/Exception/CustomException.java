package com.api.OlimpoFake.Utilities.Exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{
    public CustomException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return null;
    }
}
