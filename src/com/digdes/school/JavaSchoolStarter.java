package com.digdes.school;

import com.digdes.school.exception.handler.UnknownCommandException;
import com.digdes.school.exception.parser.MainParserException;
import com.digdes.school.exception.parser.SubcommandParserException;
import com.digdes.school.handlers.Handler;
import com.digdes.school.handlers.impl.DeleteHandler;
import com.digdes.school.handlers.impl.InsertHandler;
import com.digdes.school.handlers.impl.SelectHandler;
import com.digdes.school.handlers.impl.UpdateHandler;
import com.digdes.school.parsers.MainParser;
import com.digdes.school.repository.Repository;
import com.digdes.school.subcommands.SubcommandsMaintainer;
import com.digdes.school.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JavaSchoolStarter {

    private final MainParser mainParser = MainParser.getInstance();

    private final Map<String, Handler> handlers;

    public JavaSchoolStarter() {

        List<Handler> handlerList = List.of(
                new InsertHandler(),
                new UpdateHandler(),
                new SelectHandler(),
                new DeleteHandler()
        );

        handlers = handlerList.stream().collect(Collectors.toMap(
                Handler::getCommand, Function.identity()
        ));

        Repository repository = Repository.getInstance();
        repository.setup(Map.of(
                "id", Long.class.getName(),
                "lastName", String.class.getName(),
                "age", Long.class.getName(),
                "cost", Double.class.getName(),
                "active", Boolean.class.getName()
        ));

    }

    public List<Map<String, Object>> execute(String request) throws UnknownCommandException, MainParserException, SubcommandParserException {

        request = Utils.prepareCommand(request);
        String commandName = request.split(" ")[0].toLowerCase();

        Handler handler = handlers.get(commandName);

        if (handler == null) {
            throw new UnknownCommandException(commandName);
        }

        SubcommandsMaintainer maintainer = new SubcommandsMaintainer(new ArrayList<>());

        if (request.contains(" ")) {
            String subcommandsString = Utils.removeCommandPartOfRequest(request);

            maintainer = mainParser.parse(subcommandsString);
        }


        return handler.handle(maintainer);
    }


}
