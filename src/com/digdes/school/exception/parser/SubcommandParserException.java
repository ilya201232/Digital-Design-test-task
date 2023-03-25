package com.digdes.school.exception.parser;

public abstract class SubcommandParserException extends RuntimeException {
    public SubcommandParserException(String command) {
        super(command);
    }
}
