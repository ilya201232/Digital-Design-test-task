package com.digdes.school.exception.parser.subcommand.where;

import com.digdes.school.exception.parser.SubcommandParserException;

public class CouldNotFindCompareOperatorException extends SubcommandParserException {

    private static final String EXCEPTION_TEXT_TEMPLATE = "Couldn't find any of compare operators in string \"%s\".";

    public CouldNotFindCompareOperatorException(String dataString) {
        super(String.format(EXCEPTION_TEXT_TEMPLATE, dataString));
    }
}
