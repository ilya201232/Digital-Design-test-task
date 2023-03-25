package com.digdes.school.exception.parser.mainParser;

import com.digdes.school.exception.parser.MainParserException;

public class NoSubcommandFoundException extends MainParserException {

    private static final String EXCEPTION_TEXT_TEMPLATE = "No subcommand found is string \"%s\".";

    public NoSubcommandFoundException(String dataString) {
        super(String.format(EXCEPTION_TEXT_TEMPLATE, dataString));
    }
}
