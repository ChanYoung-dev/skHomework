package com.example.demo.UserInfo.exception;

public class NoServerException extends RuntimeException{
    public NoServerException(String message) {
        super(message);
    }

    public NoServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
