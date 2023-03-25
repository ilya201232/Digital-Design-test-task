package com.digdes.school.exception.parser.subcommand.where;

import com.digdes.school.exception.parser.SubcommandParserException;

public class UnknownCompareOperatorException extends SubcommandParserException {

    private static final String EXCEPTION_TEXT_TEMPLATE = "Unknown compare operator \"%s\" has been found in string \"%s\".";

    public UnknownCompareOperatorException(String operator, String dataString) {
        super(String.format(EXCEPTION_TEXT_TEMPLATE, operator, dataString));
    }
}