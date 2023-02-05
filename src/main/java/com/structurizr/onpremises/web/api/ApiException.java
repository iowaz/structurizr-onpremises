package com.structurizr.onpremises.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }

}
