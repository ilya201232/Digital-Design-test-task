package com.digdes.school.subcommands.impl;

import com.digdes.school.comparable.ComparableOperator;
import com.digdes.school.constant.SubcommandName;
import com.digdes.school.subcommands.Subcommand;

import java.util.Map;

public class WhereSubcommand implements Subcommand {
    private final ComparableOperator data;

    public WhereSubcommand(ComparableOperator data) {
        this.data = data;
    }
    public ComparableOperator getData() {
        return data;
    }

    @Override
    public void performChecks(Map<String, String> fieldsTypes) {
        data.InitialiseAndPerformChecks(fieldsTypes);
    }

    @Override
    public String getSubcommandName() {
        return SubcommandName.WHERE;
    }
}

