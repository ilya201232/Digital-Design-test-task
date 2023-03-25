package com.digdes.school.exception.handler;

public class UnknownCommandException extends RuntimeException {
    private final static String EXCEPTION_TEMPLATE =
            "Command \"%s\" doesn't exist.";

    public UnknownCommandException(String commandName) {
        super(String.format(EXCEPTION_TEMPLATE, commandName));
    }
}
