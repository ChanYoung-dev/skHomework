package com.example.demo.UserInfo.exception;

public class NoSignUpException extends RuntimeException{
    public NoSignUpException(String message) {
        super(message);
    }

    public NoSignUpException(String message, Throwable cause) {
        super(message, cause);
    }
}
