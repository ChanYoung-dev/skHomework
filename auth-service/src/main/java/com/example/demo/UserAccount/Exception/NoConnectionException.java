package com.example.demo.UserAccount.Exception;

public class NoConnectionException extends RuntimeException{
    public NoConnectionException() {
    }

    public NoConnectionException(String message) {
        super(message);
    }

    public NoConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
