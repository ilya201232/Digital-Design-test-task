package com.digdes.school.exception.parser.subcommand.where;

import com.digdes.school.exception.parser.SubcommandParserException;

public class NoDataPairForLogicOperatorException extends SubcommandParserException {

    private static final String EXCEPTION_TEXT_TEMPLATE = "Couldn't find pair for logic operator \"%s\" in data string \"%s\".";

    public NoDataPairForLogicOperatorException(String operator, String dataString) {
        super(String.format(EXCEPTION_TEXT_TEMPLATE, operator, dataString));
    }
}
