package com.example.demo.UserInfo.exception;

public class NoUserInfoException extends RuntimeException {
    public NoUserInfoException(String message) {
        super(message);
    }

    public NoUserInfoException(String message, Throwable cause) {
        super(message, cause);
    }
}
