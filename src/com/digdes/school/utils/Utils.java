package com.digdes.school.utils;

import com.digdes.school.exception.UnknownDataTypeException;
import com.digdes.school.object.CutString;
import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    /**
     * Prepares data string by trimming it, getting rid of spaces with length more than 1
     * and making every symbol lowercase. <br>
     * (Last 2 actions doesn't apply on parts of string surrounded by ' symbol)
     * @param data String to prepare
     * @return Modified string
     */
    public static String prepareCommand(String data) {
        data = data.trim();

        StringBuilder builder = new StringBuilder();

        char lastSymbol = '\0';
        boolean isInInnerString = false;

        for (int i = 0; i < data.length(); i++) {

            if (data.charAt(i) == '\'') {
                isInInnerString = !isInInnerString;
            }

            if (lastSymbol != ' ') {
                if (isInInnerString) {
                    builder.append(data.charAt(i));
                } else {
                    builder.append(Character.toLowerCase(data.charAt(i)));
                }
            } else if (data.charAt(i) != ' ') {
                if (isInInnerString) {
                    builder.append(data.charAt(i));
                } else {
                    builder.append(Character.toLowerCase(data.charAt(i)));
                }
            }

            lastSymbol = data.charAt(i);
        }

        return builder.toString();
    }

    public static String removeCommandPartOfRequest(String request) {
        return request.substring(request.split(" ")[0].length() + 1);
    }

    public static int getSubstringOccurrencesCount(String text, String substring) {
        text = text.toLowerCase();
        substring = substring.toLowerCase();

        int count = 0;

        int lastIndex = 0;

        while (lastIndex != -1) {
            lastIndex = text.indexOf(substring, lastIndex);

            if (lastIndex != -1) {
                count++;
                lastIndex += substring.length();
            }
        }

        return count;
    }

    public static boolean isLong(String s) {
        try {
            Long.parseLong(s);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }

        return true;
    }

    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }

        return true;
    }

    public static Object parseValue(String value) throws UnknownDataTypeException {
        Object parsedValue;

        if (value.contains("'")) {
            parsedValue = value.substring(1, value.length() - 1);
        } else if (Utils.isLong(value)) {
            parsedValue = Long.parseLong(value);
        } else if (Utils.isDouble(value)) {
            parsedValue = Double.parseDouble(value);
        } else if (value.equalsIgnoreCase("false") || value.equalsIgnoreCase("true")) {
            parsedValue = Boolean.parseBoolean(value);
        } else if (value.equalsIgnoreCase("null")) {
            parsedValue = null;
        } else {
            throw new UnknownDataTypeException(value);
        }

        return parsedValue;
    }

    public static CutString removeStrings(String data) {
        StringBuilder builder = new StringBuilder();

        Map<Integer, Integer> cuts = new HashMap<>();

        boolean isSkipping = false;

        int start = -1;
        int end;


        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == '\'') {
                if (isSkipping) {
                    end = i;
                    cuts.put(start, end);
                } else {
                    start = i;
                }
                isSkipping = !isSkipping;
            }

            if (!isSkipping && data.charAt(i) != '\'') {
                builder.append(data.charAt(i));
            }
        }

        return new CutString(builder.toString(), cuts, data.length());
    }

    public static int searchForPair(String data, int startIndex) {

        List<Character> pairs = new ArrayList<>();

        boolean isSkipping = false;

        pairs.add('(');

        for (int i = startIndex + 1; i < data.length(); i++) {
            if (data.charAt(i) == '\'') {
                isSkipping = !isSkipping;
            }

            if (!isSkipping && data.charAt(i) != '\'') {
                if (data.charAt(i) == '(') {
                    pairs.add('(');
                } else if (data.charAt(i) == ')') {
                    pairs.remove(pairs.size() - 1);

                    if (pairs.size() == 0) {
                        return i;
                    }
                }
            }
        }

        return -1;
    }

}
