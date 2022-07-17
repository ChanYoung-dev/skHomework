package com.example.demo.UserAccount.Exception;

public class InfoServerException extends RuntimeException{
    public InfoServerException(String message) {
        super(message);
    }

    public InfoServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
