package com.bookproject.exception;

public class RequestValidationException extends Exception {

    private static final long serialVersionUID = 7883533894052698755L;

    public RequestValidationException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
