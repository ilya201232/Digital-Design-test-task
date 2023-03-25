package com.digdes.school.comparable.impl;

import com.digdes.school.comparable.ComparableOperator;
import com.digdes.school.constant.ComparableOperatorName;
import com.digdes.school.exception.comparison.*;

import java.util.Map;

public class OrOperator implements ComparableOperator {

    private final Object first;
    private final Object second;

    private ComparableOperator firstOperator;
    private ComparableOperator secondOperator;

    public OrOperator(Object first, Object second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean compare(Map<String, Object> row) {
        return firstOperator.compare(row) || secondOperator.compare(row);
    }

    @Override
    public String getOperation() {
        return ComparableOperatorName.OR;
    }

    @Override
    public void InitialiseAndPerformChecks(Map<String, String> fieldsTypes) throws
            IllegalTypeInComparisonException,
            NullValueInComparisonException, NoCollectionReferenceInComparisonException,
            MoreThanOneCollectionReferenceInComparisonException {
        if (first instanceof ComparableOperator operator1 && second instanceof ComparableOperator operator2) {
            firstOperator = operator1;
            secondOperator = operator2;
        } else {
            throw new IllegalTypeInComparisonException(getOperation());
        }

        firstOperator.InitialiseAndPerformChecks(fieldsTypes);
        secondOperator.InitialiseAndPerformChecks(fieldsTypes);
    }
}
