package com.digdes.school.exception.parser.subcommand.where;

import com.digdes.school.exception.parser.SubcommandParserException;

public class BracesNoPairException extends SubcommandParserException {

    private static final String EXCEPTION_TEXT_TEMPLATE = "Couldn't find pair for '(' symbol. Search string: \"%s\".";

    public BracesNoPairException(String searchString) {
        super(String.format(EXCEPTION_TEXT_TEMPLATE, searchString));
    }
}
