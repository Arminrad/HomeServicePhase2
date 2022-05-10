package com.phase2.homeService.exception;

public class InvalidNationalCodeException extends RuntimeException {

    public InvalidNationalCodeException() { super("Your nationalCode is not valid!");}

    public InvalidNationalCodeException(String message) {
        super(message);
    }

    public InvalidNationalCodeException(String message, Throwable cause) {
        super(message, cause);
    }

}

