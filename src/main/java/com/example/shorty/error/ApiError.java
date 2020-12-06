package com.example.shorty.error;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ApiError {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private final String message;
    private final String debugMessage;

    ApiError(String message, Throwable ex) {
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

}
