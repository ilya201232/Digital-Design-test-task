package com.digdes.school.exception.parser.subcommand.value;

import com.digdes.school.exception.parser.SubcommandParserException;

public class NoCollectionReferenceInValueException extends SubcommandParserException {

    private final static String EXCEPTION_TEMPLATE = """
            Part of data from values subcommand doesn't have reference for collection.
            Data part: %s
            """;

    public NoCollectionReferenceInValueException(String dataPart) {
        super(String.format(EXCEPTION_TEMPLATE, dataPart));
    }

    public NoCollectionReferenceInValueException(String field, String valueString) {
        super(String.format(EXCEPTION_TEMPLATE, field + " = " + valueString));
    }

}
