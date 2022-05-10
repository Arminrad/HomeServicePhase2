package com.phase2.homeService.exception;

public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException() { super("You entered an invalid password!");}

    public InvalidPasswordException(String message) {
        super(message);
    }

    public InvalidPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

}

