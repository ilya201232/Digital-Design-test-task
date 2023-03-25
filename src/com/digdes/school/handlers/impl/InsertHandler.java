package com.digdes.school.handlers.impl;

import com.digdes.school.constant.CommandName;
import com.digdes.school.constant.SubcommandName;
import com.digdes.school.exception.handler.NotEnoughDataForCommandException;
import com.digdes.school.exception.handler.TooManySubcommandsForCommandException;
import com.digdes.school.handlers.Handler;
import com.digdes.school.repository.Repository;
import com.digdes.school.subcommands.SubcommandsMaintainer;
import com.digdes.school.subcommands.impl.ValuesSubcommand;

import java.util.List;
import java.util.Map;

public class InsertHandler implements Handler {

    Repository repository = Repository.getInstance();

    @Override
    public List<Map<String, Object>> handle(SubcommandsMaintainer maintainer) throws TooManySubcommandsForCommandException, NotEnoughDataForCommandException {

        if (maintainer.isAnyApartFrom(List.of(SubcommandName.VALUES))) {
            throw new TooManySubcommandsForCommandException("Only \"Values\" subcommand was expected.");
        }

        ValuesSubcommand valuesSubcommand = (ValuesSubcommand) maintainer.getSubcommand(SubcommandName.VALUES);

        if (valuesSubcommand != null) {
            valuesSubcommand.performChecks(repository.getFieldList());

            return repository.createRow(valuesSubcommand.getData());
        }

        throw new NotEnoughDataForCommandException("Insert handler requires values subcommand data to work.");
    }

    @Override
    public String getCommand() {
        return CommandName.INSERT;
    }
}
