package com.example.telecomdemo.exception;


public class DataNotExistException extends RuntimeException {
    public DataNotExistException(String message) {
        super(message);
    }
}
