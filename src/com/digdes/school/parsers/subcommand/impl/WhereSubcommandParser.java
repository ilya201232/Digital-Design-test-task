package com.digdes.school.parsers.subcommand.impl;

import com.digdes.school.comparable.ComparableOperator;
import com.digdes.school.comparable.ComparableOperatorFactory;
import com.digdes.school.constant.ComparableOperatorName;
import com.digdes.school.constant.SubcommandName;
import com.digdes.school.exception.parser.SubcommandParserException;
import com.digdes.school.exception.parser.subcommand.where.*;
import com.digdes.school.object.CutString;
import com.digdes.school.parsers.subcommand.SubcommandParser;
import com.digdes.school.subcommands.Subcommand;
import com.digdes.school.subcommands.impl.WhereSubcommand;
import com.digdes.school.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class WhereSubcommandParser implements SubcommandParser {

    List<String> comparableOperatorsWithSpace = List.of(
            ComparableOperatorName.LIKE,
            ComparableOperatorName.ILIKE
    );
    List<String> comparableOperatorsWithoutSpace = List.of(
            ComparableOperatorName.EQUALS,
            ComparableOperatorName.NOT_EQUALS,
            ComparableOperatorName.GREATER,
            ComparableOperatorName.GREATER_OR_EQUAL,
            ComparableOperatorName.LESS,
            ComparableOperatorName.LESS_OR_EQUAL
    );
    List<String> comparableLogicOperators = List.of(
            ComparableOperatorName.AND,
            ComparableOperatorName.OR
    );

    private final ComparableOperatorFactory comparableOperatorFactory;

    public WhereSubcommandParser() {
        comparableOperatorFactory = new ComparableOperatorFactory();
    }

    @Override
    public Subcommand parse(String data) throws SubcommandParserException {
        return new WhereSubcommand(privateParse(data));
    }

    private ComparableOperator privateParse(String data) throws
            BracesNoPairException, NoDataPairForLogicOperatorException,
            CouldNotFindLogicOperatorException, CouldNotFindCompareOperatorException, UnknownCompareOperatorException {
        data = data.trim();

        if (containsAnyLogicOperator(data)) {
            if (data.charAt(0) == '(') {
                int pair1End = Utils.searchForPair(data, 0);

                if (pair1End != -1) {
                    ComparableOperator operator1 = privateParse(data.substring(1, pair1End));
                    ComparableOperator operator2 = null;
                    String operatorName = "";

                    int pair2Start = data.indexOf("(", pair1End);

                    if (pair2Start != -1) {
                        int pair2End = Utils.searchForPair(data, pair2Start);

                        operatorName = data.substring(pair1End + 1, pair2Start).trim();

                        if (pair2End == -1) {
                            throw new BracesNoPairException(data);
                        }

                        String secondPart = data.substring(pair2Start + 1, pair2End).trim();

                        if (secondPart.isEmpty()) {
                            throw new NoDataPairForLogicOperatorException(operatorName, data);
                        }

                        operator2 = privateParse(secondPart);

                    } else {
                        int index = -1;

                        for (String operator : comparableLogicOperators) {
                            int newIndex = Utils.removeStrings(data).indexOfInOrigIndex(operator, pair1End);
                            if (newIndex != -1) {
                                index = newIndex;
                                operatorName = operator;
                                break;
                            }
                        }

                        if (index != -1) {
                            String secondPart = data.substring(index + operatorName.length()).trim();

                            if (secondPart.isEmpty()) {
                                throw new NoDataPairForLogicOperatorException(operatorName, data);
                            }

                            operator2 = privateParse(secondPart);
                        } else {
                            throw new CouldNotFindLogicOperatorException(data, "(After first braces)");
                        }
                    }

                    return comparableOperatorFactory.createOperator(operatorName, operator1, operator2);
                } else {
                    throw new BracesNoPairException(data);
                }
            } else {
                String operatorName = "";
                int index = -1;

                for (String operator : comparableLogicOperators) {
                    int newIndex = Utils.removeStrings(data).indexOfInOrigIndex(operator);
                    if (newIndex != -1) {
                        index = newIndex;
                        operatorName = operator;
                        break;
                    }
                }
                if (index != -1) {
                    ComparableOperator operator1 = privateParse(data.substring(0, index).trim());
                    ComparableOperator operator2;

                    int pair2Start = data.indexOf("(", index + operatorName.length() - 1);

                    if (pair2Start != -1) {
                        int pair2End = Utils.searchForPair(data, pair2Start);

                        if (pair2End == -1) throw new BracesNoPairException(data);

                        operator2 = privateParse(data.substring(pair2Start + 1, pair2End));
                    } else {
                        operator2 = privateParse(data.substring(index + operatorName.length()).trim());

                    }

                    return comparableOperatorFactory.createOperator(
                            operatorName,
                            operator1,
                            operator2);
                } else {
                    throw new CouldNotFindLogicOperatorException(data);
                }
            }
        } else {
            for (String command : comparableOperatorsWithSpace) {
                String searchPattern = " " + command + " ";
                int startIndex = Utils.removeStrings(data).indexOfInOrigIndex(searchPattern);
                if (startIndex != -1) {
                    return comparableOperatorFactory.createOperator(command,
                            Utils.parseValue(data.substring(0, startIndex)),
                            Utils.parseValue(data.substring(startIndex + command.length() + 2)));
                }
            }

            List<Character> symbolsToSearch = new ArrayList<>();
            for (String command: comparableOperatorsWithoutSpace) {
                for (char symbol : command.toCharArray()) {
                    if (!symbolsToSearch.contains(symbol)) {
                        symbolsToSearch.add(symbol);
                    }
                }
            }

            int startIndex = data.length();
            int endIndex = -1;
            CutString tmpData = Utils.removeStrings(data);

            for (char symbol : symbolsToSearch) {
                int newStartIndex = tmpData.indexOfInOrigIndex(symbol);

                if (newStartIndex != -1)
                    startIndex = Math.min(startIndex, newStartIndex);

                endIndex = Math.max(endIndex, tmpData.lastIndexOfInOrigIndex(symbol));
            }

            if (startIndex > endIndex + 1) {
                throw new CouldNotFindCompareOperatorException(data);
            }

            String possibleCommand = data.substring(startIndex, endIndex + 1);

            if (commandWithoutSpaceExists(possibleCommand)) {

                return comparableOperatorFactory.createOperator(
                        possibleCommand,
                        Utils.parseValue(data.substring(0, startIndex).trim()),
                        Utils.parseValue(data.substring(startIndex + possibleCommand.length()).trim())
                );
            } else {
                throw new UnknownCompareOperatorException(possibleCommand, data);
            }
        }
    }

    private boolean commandWithoutSpaceExists(String possibleCommand) {
        for (String command :
                comparableOperatorsWithoutSpace) {
            if (command.equals(possibleCommand)) {
                return true;
            }
        }

        return false;
    }

    private boolean containsAnyLogicOperator(String data) {
        for (String logicOperator : comparableLogicOperators) {
            if (Utils.removeStrings(data).getData().contains(logicOperator)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getSubcommandName() {
        return SubcommandName.WHERE;
    }
}
