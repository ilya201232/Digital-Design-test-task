package com.digdes.school.handlers.impl;

import com.digdes.school.comparable.ComparableOperator;
import com.digdes.school.comparable.impl.AlwaysTrueOperator;
import com.digdes.school.constant.CommandName;
import com.digdes.school.constant.SubcommandName;
import com.digdes.school.exception.comparison.*;
import com.digdes.school.exception.handler.NotEnoughDataForCommandException;
import com.digdes.school.exception.handler.TooManySubcommandsForCommandException;
import com.digdes.school.handlers.Handler;
import com.digdes.school.parsers.MainParser;
import com.digdes.school.repository.Repository;
import com.digdes.school.subcommands.SubcommandsMaintainer;
import com.digdes.school.subcommands.impl.ValuesSubcommand;
import com.digdes.school.subcommands.impl.WhereSubcommand;

import java.util.List;
import java.util.Map;

public class UpdateHandler implements Handler {

    Repository repository = Repository.getInstance();

    @Override
    public List<Map<String, Object>> handle(SubcommandsMaintainer maintainer)  throws TooManySubcommandsForCommandException, NotEnoughDataForCommandException {

        if (maintainer.isAnyApartFrom(List.of(SubcommandName.WHERE, SubcommandName.VALUES))) {
            throw new TooManySubcommandsForCommandException("Only \"Where\" and \"Values\" subcommands were expected.");
        }

        ValuesSubcommand valuesSubcommand = (ValuesSubcommand) maintainer.getSubcommand(SubcommandName.VALUES);
        WhereSubcommand whereSubcommand = (WhereSubcommand) maintainer.getSubcommand(SubcommandName.WHERE);

        ComparableOperator comparableOperator = new AlwaysTrueOperator();

        if (valuesSubcommand != null) {
            valuesSubcommand.performChecks(repository.getFieldList());

            if (whereSubcommand != null) {
                whereSubcommand.performChecks(repository.getFieldList());

                comparableOperator = whereSubcommand.getData();
            }

            return repository.update(valuesSubcommand.getData(), comparableOperator);
        }

        throw new NotEnoughDataForCommandException("Update handler requires values subcommand data to work.");
    }

    @Override
    public String getCommand() {
        return CommandName.UPDATE;
    }
}
