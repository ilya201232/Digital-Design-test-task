package com.digdes.school.parsers;

import com.digdes.school.exception.parser.MainParserException;
import com.digdes.school.exception.parser.mainParser.NoSubcommandFoundException;
import com.digdes.school.exception.parser.mainParser.SubcommandDuplicateFoundException;
import com.digdes.school.exception.parser.mainParser.SubcommandNoDataFoundException;
import com.digdes.school.exception.parser.SubcommandParserException;
import com.digdes.school.parsers.subcommand.SubcommandParser;
import com.digdes.school.parsers.subcommand.SubcommandParserData;
import com.digdes.school.parsers.subcommand.impl.ValuesSubcommandParser;
import com.digdes.school.parsers.subcommand.impl.WhereSubcommandParser;
import com.digdes.school.subcommands.Subcommand;
import com.digdes.school.subcommands.SubcommandsMaintainer;
import com.digdes.school.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MainParser {

    private static MainParser instance;

    private final Map<String, SubcommandParser> parsers;

    public static MainParser getInstance() {

        if (instance == null) {
            instance = new MainParser(List.of(
                    new ValuesSubcommandParser(),
                    new WhereSubcommandParser()
            ));
        }

        return instance;
    }

    private MainParser(List<SubcommandParser> parsers) {
        this.parsers = parsers.stream().collect(Collectors.toMap(
                SubcommandParser::getSubcommandName, Function.identity()
        ));
    }

    public SubcommandsMaintainer parse(String subcommandsString) throws MainParserException, SubcommandParserException {
        List<Subcommand> subcommands = new ArrayList<>();

        List<SubcommandParserData> dataList = new ArrayList<>();

        for (SubcommandParser parser : parsers.values()) {

            int count = Utils.getSubstringOccurrencesCount(subcommandsString, parser.getSubcommandName() + " ");

            if (count > 1) {
                throw new SubcommandDuplicateFoundException(parser.getSubcommandName());
            } else if (count == 1) {
                dataList.add(new SubcommandParserData(
                        parser.getSubcommandName(),
                        subcommandsString.indexOf(parser.getSubcommandName() + " ")
                ));
            }

        }

        if (dataList.size() == 0) {
            throw new NoSubcommandFoundException(subcommandsString);
        }

        dataList.sort((el1, el2) -> {
            if (el1.getIndex() > el2.getIndex()) {
                return 1;
            } else if (el1.getIndex() == el2.getIndex()) {
                return 0;
            }

            return -1;
        });

        for (int i = 0; i < dataList.size() - 1; i++) {
            int startIndex = dataList.get(i).getIndex() + dataList.get(i).getSubcommandName().length() + 1;
            int endIndex = dataList.get(i + 1).getIndex() - 1;

            if (startIndex >= endIndex) {
                throw new SubcommandNoDataFoundException(dataList.get(i).getSubcommandName());
            }

            dataList.get(i).setDataString(subcommandsString.substring(startIndex, endIndex));
        }

        int startIndex = dataList.get(dataList.size() - 1).getIndex() + dataList.get(dataList.size() - 1).getSubcommandName().length() + 1;

        if (startIndex < subcommandsString.length())
            dataList.get(dataList.size() - 1).setDataString(subcommandsString.substring(startIndex));
        else
            throw new SubcommandNoDataFoundException(dataList.get(dataList.size() - 1).getSubcommandName());

//        System.out.println("Subcommands:");
//        for (SubcommandParserData parserData : dataList) {
//            System.out.println("\tSubcommand Name: " + parserData.getSubcommandName());
//            System.out.println("\tData String: " + parserData.getDataString());
//            System.out.println("\tIndex: " + parserData.getIndex());
//            System.out.println();
//        }

//        System.out.println();

        for (SubcommandParserData parserData : dataList) {
            subcommands.add(parsers.get(parserData.getSubcommandName()).parse(parserData.getDataString()));
        }

        return new SubcommandsMaintainer(subcommands);
    }

}
