package com.digdes.school.exception.handler;

public class NotEnoughDataForCommandException extends RuntimeException {

    public NotEnoughDataForCommandException(String message) {
        super(message);
    }
}
