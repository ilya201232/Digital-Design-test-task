package com.digdes.school.exception.parser.mainParser;

import com.digdes.school.exception.parser.MainParserException;

public class SubcommandNoDataFoundException extends MainParserException {

    private final static String EXCEPTION_TEMPLATE =
            "Data for subcommand \"%s\" has not been found.";

    public SubcommandNoDataFoundException(String subcommandName) {
        super(String.format(EXCEPTION_TEMPLATE, subcommandName));
    }
}

