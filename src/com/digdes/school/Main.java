package com.digdes.school;

import com.digdes.school.utils.Utils;
import jdk.jshell.execution.Util;

import java.lang.reflect.Type;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        JavaSchoolStarter starter = new JavaSchoolStarter();

        while (true) {
            String command = scanner.nextLine();

            if (Objects.equals(command, "EXIT")) {
                break;
            } else {
                System.out.println("Запрос: " + command);

                try {
                    printResult(starter.execute(command));
                } catch (Exception exception) {
                    System.out.println("\tОшибка: " + exception.getMessage());
                }
            }
        }

    }

    private static void printResult(List<Map<String, Object>> result) {

        List<String> fields = new ArrayList<>();

        for (Map<String, Object> row : result) {
            for (String fieldName : row.keySet()) {
                if (!fields.contains(fieldName)) {
                    fields.add(fieldName);
                }
            }
        }

        /*Map<String, Integer> maxLengths = new HashMap<>();
        for (Map<String, Object> row : result)
        {
            for (String fieldName : row.keySet())
            {
                if (maxLengths.get(fieldName) < row.get(fieldName).toString().length()) {
                    maxLengths.replace(fieldName, row.get(fieldName).toString().length());
                }
            }
        }*/

        for (String fieldName : fields)
        {
            System.out.format("%15s", fieldName);
        }

        System.out.println();

        for (Map<String, Object> row : result)
        {
            for (String fieldName : fields)
            {
                if (row.containsKey(fieldName)) {
                    System.out.format("%15s", row.get(fieldName).toString());
                } else {
                    System.out.format("%15s", "");
                }
            }
            System.out.println();

        }
        System.out.println();


    }
}