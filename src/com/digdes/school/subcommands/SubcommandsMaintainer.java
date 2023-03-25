package com.digdes.school.subcommands;

import java.util.List;
import java.util.Objects;

public class SubcommandsMaintainer {

    private final List<Subcommand> subcommandList;

    public SubcommandsMaintainer(List<Subcommand> subcommandList) {
        this.subcommandList = subcommandList;
    }

    public Subcommand getSubcommand(String subcommandName) {
        Subcommand result = null;

        for (Subcommand command: subcommandList) {
            if (Objects.equals(command.getSubcommandName(), subcommandName)) {

                result = command;
            }
        }

        return result;
    }

    public boolean isAnyApartFrom(List<String> expectedSubcommand) {
        int subcommandsCount = subcommandList.size();

        for (String subcommandName : expectedSubcommand) {
            if (getSubcommand(subcommandName) != null) {
                subcommandsCount--;
            }
        }

        return subcommandsCount > 0;
    }
}
