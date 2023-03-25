package com.digdes.school.exception.parser.subcommand.value;

import com.digdes.school.exception.parser.SubcommandParserException;

public class WrongValueOperationFormatException extends SubcommandParserException {

    private final static String EXCEPTION_TEMPLATE =
            "Value operator \"%s\" does not correspond to <collection_field>=<value> pattern.";

    public WrongValueOperationFormatException(String subcommandName) {
        super(String.format(EXCEPTION_TEMPLATE, subcommandName));
    }
}

