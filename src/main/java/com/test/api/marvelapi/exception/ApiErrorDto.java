package com.test.api.marvelapi.exception;

public record ApiErrorDto (
        String message,
        String backendMessage,
        String method,
        String url
){
}
