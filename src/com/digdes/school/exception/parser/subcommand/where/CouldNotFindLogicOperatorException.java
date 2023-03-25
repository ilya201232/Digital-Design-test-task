package com.digdes.school.exception.parser.subcommand.where;

import com.digdes.school.exception.parser.SubcommandParserException;

public class CouldNotFindLogicOperatorException extends SubcommandParserException {

    private static final String EXCEPTION_TEXT_TEMPLATE = "Couldn't find any of logic operators in string \"%s\". %s";

    public CouldNotFindLogicOperatorException(String dataString, String additionMessage) {
        super(String.format(EXCEPTION_TEXT_TEMPLATE, dataString, additionMessage));
    }

    public CouldNotFindLogicOperatorException(String dataString) {
        super(String.format(EXCEPTION_TEXT_TEMPLATE, dataString, ""));
    }
}
