package com.example.demo.UserAccount.Exception;

public class NoSignUpException extends RuntimeException{
    public NoSignUpException(String message) {
        super(message);
    }

    public NoSignUpException(String message, Throwable cause) {
        super(message, cause);
    }
}
