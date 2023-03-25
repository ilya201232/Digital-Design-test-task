package com.digdes.school.subcommands;

import java.util.Map;

public interface Subcommand {

    String getSubcommandName();

    void performChecks(Map<String, String> fieldsTypes);
}
