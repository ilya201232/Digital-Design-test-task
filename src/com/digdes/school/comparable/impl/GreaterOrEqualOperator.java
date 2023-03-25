package com.digdes.school.comparable.impl;

import com.digdes.school.comparable.ComparableOperator;
import com.digdes.school.constant.ComparableOperatorName;
import com.digdes.school.exception.comparison.*;
import com.digdes.school.repository.Repository;

import java.util.Map;

public class GreaterOrEqualOperator implements ComparableOperator {

    private final Object first;
    private final Object second;

    private String rowFieldName;
    private boolean isRowFieldWasLeft;

    private Object compareValue;

    public GreaterOrEqualOperator(Object first, Object second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean compare(Map<String, Object> row) {
        Object rowValue = row.get(rowFieldName);

        if (rowValue == null)
            return false;

        Number rowNumber = (Number) rowValue;
        Number compareValueNumber = (Number) compareValue;

        int result;

        if (isRowFieldWasLeft) {
            result = Double.compare(rowNumber.doubleValue(), compareValueNumber.doubleValue());
        } else {
            result = Double.compare(compareValueNumber.doubleValue(), rowNumber.doubleValue());
        }

        return result >= 0;
    }

    @Override
    public String getOperation() {
        return ComparableOperatorName.GREATER_OR_EQUAL;
    }

    @Override
    public void InitialiseAndPerformChecks(Map<String, String> fieldsTypes) throws
            IllegalTypeInComparisonException,
            NullValueInComparisonException, NoCollectionReferenceInComparisonException,
            MoreThanOneCollectionReferenceInComparisonException {
        if (first == null) throw new NullValueInComparisonException(getOperation());
        if (second == null) throw new NullValueInComparisonException(getOperation());

        boolean hasFound = false;

        if (first instanceof String firstString && fieldsTypes.containsKey(firstString)) {
            hasFound = true;

            isRowFieldWasLeft = true;

            rowFieldName = firstString;
            compareValue = second;
        }

        if (second instanceof String secondString && fieldsTypes.containsKey(secondString)) {
            if (hasFound) {
                throw new MoreThanOneCollectionReferenceInComparisonException(getOperation());
            } else {
                hasFound = true;

                isRowFieldWasLeft = false;

                rowFieldName = secondString;
                compareValue = first;
            }
        }

        if (!hasFound) {
            throw new NoCollectionReferenceInComparisonException(getOperation());
        }

        String fieldType = fieldsTypes.get(rowFieldName);
        String valueType = compareValue.getClass().getName();

        if (!(fieldType.equals(Long.class.getName()) || fieldType.equals(Double.class.getName()))) {
            throw new IllegalTypeInComparisonException(getOperation());
        }

        if (!(valueType.equals(Long.class.getName()) || valueType.equals(Double.class.getName()))) {
            throw new IllegalTypeInComparisonException(getOperation());
        }
    }
}
