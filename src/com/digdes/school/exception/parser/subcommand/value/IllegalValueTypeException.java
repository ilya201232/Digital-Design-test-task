package com.digdes.school.exception.parser.subcommand.value;

import com.digdes.school.exception.parser.SubcommandParserException;

public class IllegalValueTypeException extends SubcommandParserException {

    private final static String EXCEPTION_TEMPLATE = """
            Illegal field - value type.
            Field name: %s, value string: %s.
            Field type: %s, value type: %s.
            """;

    public IllegalValueTypeException(String fieldName, String valueString, String fieldType, String valueType) {
        super(String.format(EXCEPTION_TEMPLATE, fieldName, valueString, fieldType, valueType));
    }
}