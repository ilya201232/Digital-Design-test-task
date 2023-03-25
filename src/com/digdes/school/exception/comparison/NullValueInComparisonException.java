package com.digdes.school.exception.comparison;

public class NullValueInComparisonException extends RuntimeException {

    private static final String EXCEPTION_TEXT_TEMPLATE = "Comparison operator '%s' found null value.";

    public NullValueInComparisonException(String comparisonOperatorName) {
        super(String.format(EXCEPTION_TEXT_TEMPLATE, comparisonOperatorName));
    }
}