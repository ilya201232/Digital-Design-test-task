package com.digdes.school.exception;

public class UnknownDataTypeException extends RuntimeException {
    private final static String EXCEPTION_TEMPLATE =
            "Cannot identify type of object from string \"%s\"";

    public UnknownDataTypeException(String objectString) {
        super(String.format(EXCEPTION_TEMPLATE, objectString));
    }
}
