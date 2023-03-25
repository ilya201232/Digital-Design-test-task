package com.digdes.school.comparable.impl;

import com.digdes.school.comparable.ComparableOperator;
import com.digdes.school.constant.ComparableOperatorName;
import com.digdes.school.exception.comparison.*;

import java.util.Map;

public class EqualsOperator implements ComparableOperator {

    private final Object first;
    private final Object second;

    private String rowFieldName;
    private Object compareValue;

    public EqualsOperator(Object first, Object second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean compare(Map<String, Object> row) {

        Object rowValue = row.get(rowFieldName);

        if (rowValue == null) {
            return false;
        }

        return rowValue.equals(compareValue);
    }

    @Override
    public String getOperation() {
        return ComparableOperatorName.EQUALS;
    }

    @Override
    public void InitialiseAndPerformChecks(Map<String, String> fieldsTypes) throws
            NullValueInComparisonException, NoCollectionReferenceInComparisonException,
            MoreThanOneCollectionReferenceInComparisonException {
        if (first == null) throw new NullValueInComparisonException(getOperation());
        if (second == null) throw new NullValueInComparisonException(getOperation());

        boolean hasFound = false;

        if (first instanceof String firstString && fieldsTypes.containsKey(firstString)) {
            hasFound = true;

            rowFieldName = firstString.toLowerCase();
            compareValue = second;
        }

        if (second instanceof String secondString && fieldsTypes.containsKey(secondString)) {
            if (hasFound) {
                throw new MoreThanOneCollectionReferenceInComparisonException(getOperation());
            } else {
                hasFound = true;

                rowFieldName = secondString.toLowerCase();
                compareValue = first;
            }
        }

        if (!hasFound) {
            throw new NoCollectionReferenceInComparisonException(getOperation());
        }
    }
}
