package com.digdes.school.parsers.subcommand;

import com.digdes.school.exception.parser.SubcommandParserException;
import com.digdes.school.subcommands.Subcommand;

public interface SubcommandParser {

    Subcommand parse(String data) throws SubcommandParserException;

    String getSubcommandName();
}
