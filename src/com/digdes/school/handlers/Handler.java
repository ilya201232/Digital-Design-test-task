package com.digdes.school.handlers;

import com.digdes.school.exception.handler.NotEnoughDataForCommandException;
import com.digdes.school.exception.handler.TooManySubcommandsForCommandException;
import com.digdes.school.subcommands.SubcommandsMaintainer;

import java.util.List;
import java.util.Map;

public interface Handler {
    List<Map<String, Object>> handle(SubcommandsMaintainer maintainer) throws TooManySubcommandsForCommandException, NotEnoughDataForCommandException;

    String getCommand();
}
