package com.digdes.school.exception.parser.subcommand.value;

import com.digdes.school.exception.parser.SubcommandParserException;

public class NullValueInValuesException extends SubcommandParserException {

    private static final String EXCEPTION_TEXT_TEMPLATE = "Cannot assign null value to field %s.";

    public NullValueInValuesException(String fieldName) {
        super(String.format(EXCEPTION_TEXT_TEMPLATE, fieldName));
    }
}
