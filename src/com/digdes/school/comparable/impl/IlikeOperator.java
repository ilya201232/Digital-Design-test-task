package com.digdes.school.comparable.impl;

import com.digdes.school.comparable.ComparableOperator;
import com.digdes.school.constant.ComparableOperatorName;
import com.digdes.school.exception.comparison.*;
import com.digdes.school.repository.Repository;

import java.util.Map;
import java.util.regex.Pattern;

public class IlikeOperator implements ComparableOperator {

    private final Object first;
    private final Object second;

    private String rowFieldName;
    private String compareValue;

    public IlikeOperator(Object first, Object second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean compare(Map<String, Object> row) {
        compareValue = compareValue.replaceAll("%", ".*");

        Pattern pattern = Pattern.compile(compareValue);
        return !pattern.matcher((String) row.get(rowFieldName)).matches();
    }

    @Override
    public String getOperation() {
        return ComparableOperatorName.ILIKE;
    }

    @Override
    public void InitialiseAndPerformChecks(Map<String, String> fieldsTypes) throws
            IllegalTypeInComparisonException,
            NullValueInComparisonException, NoCollectionReferenceInComparisonException {
        if (first == null) throw new NullValueInComparisonException(getOperation());
        if (second == null) throw new NullValueInComparisonException(getOperation());

        if (first instanceof String firstString && fieldsTypes.containsKey(firstString)) {
            rowFieldName = firstString;
        } else {
            throw new NoCollectionReferenceInComparisonException(getOperation());
        }

        if (second instanceof String secondString) {
            compareValue = secondString;
        } else {
            throw new IllegalTypeInComparisonException(getOperation());
        }


        String fieldType = fieldsTypes.get(rowFieldName);

        if (!fieldType.equals(String.class.getName())) {
            throw new IllegalTypeInComparisonException(getOperation());
        }
    }
}
