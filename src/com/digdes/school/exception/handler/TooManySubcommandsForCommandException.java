package com.digdes.school.exception.handler;

public class TooManySubcommandsForCommandException extends RuntimeException {

    public TooManySubcommandsForCommandException(String message) {
        super(message);
    }
}