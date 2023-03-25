package com.digdes.school.repository;

import com.digdes.school.comparable.ComparableOperator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository {

    private final List<Map<String, Object>> collection;

    private Map<String, String> fieldList;

    private static Repository instance;

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }

        return instance;
    }

    private Repository() {
        collection = new ArrayList<>();
    }


    public void setup(Map<String, String> fields) {
        fieldList = fields;
    }

    public List<Map<String, Object>> createRow(Map<String, Object> fields) {
        Map<String, Object> row = new HashMap<>();
        Map<String, Object> outputData = new HashMap<>();

        for (String fieldName : fieldList.keySet()) {
            if (fields.containsKey(fieldName) && fields.get(fieldName) != null) {
                row.put(fieldName, fields.get(fieldName));
                outputData.put(fieldName, fields.get(fieldName));
            } else {
                row.put(fieldName, null);
            }
        }

        collection.add(row);

        return List.of(outputData);
    }

    public List<Map<String, Object>> update(Map<String, Object> fields, ComparableOperator comparableOperator) {
        List<Map<String, Object>> result = new ArrayList<>();

        for (Map<String, Object> row : collection) {
            if (comparableOperator.compare(row)) {
                for (String fieldName : fields.keySet()) {
                    row.replace(fieldName, fields.get(fieldName));
                }

                Map<String, Object> outputRow = new HashMap<>();

                for (String fieldName : row.keySet()) {
                    if (row.get(fieldName) != null)
                        outputRow.put(fieldName, row.get(fieldName));
                }

                result.add(outputRow);
            }
        }

        return result;
    }

    public List<Map<String, Object>> select(ComparableOperator comparableOperator) {
        List<Map<String, Object>> result = new ArrayList<>();

        for (Map<String, Object> row : collection) {
            if (comparableOperator.compare(row)) {
                Map<String, Object> outputRow = new HashMap<>();

                for (String fieldName : row.keySet()) {
                    if (row.get(fieldName) != null)
                        outputRow.put(fieldName, row.get(fieldName));
                }

                result.add(outputRow);
            }
        }

        return result;
    }

    public List<Map<String, Object>> delete(ComparableOperator comparableOperator) {
        List<Map<String, Object>> result = new ArrayList<>();

        for (Map<String, Object> row : collection) {
            if (comparableOperator.compare(row)) {
                Map<String, Object> outputRow = new HashMap<>();

                for (String fieldName : row.keySet()) {
                    if (row.get(fieldName) != null)
                        outputRow.put(fieldName, row.get(fieldName));
                }

                result.add(outputRow);
            }
        }

        collection.removeIf(comparableOperator::compare);

        return result;
    }

    public Map<String, String> getFieldList() {
        return fieldList;
    }


}
