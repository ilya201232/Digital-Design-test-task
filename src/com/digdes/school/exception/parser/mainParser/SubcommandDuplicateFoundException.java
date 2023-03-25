package com.digdes.school.exception.parser.mainParser;

import com.digdes.school.exception.parser.MainParserException;

public class SubcommandDuplicateFoundException extends MainParserException {

    private final static String EXCEPTION_TEMPLATE =
            "Subcommand \"%s\" has been found more than one time.";

    public SubcommandDuplicateFoundException(String subcommandName) {
        super(String.format(EXCEPTION_TEMPLATE, subcommandName));
    }
}
