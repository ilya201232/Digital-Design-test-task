package com.digdes.school.exception.comparison;

public class IllegalTypeInComparisonException extends RuntimeException {

    private static final String EXCEPTION_TEXT_TEMPLATE = "Comparison operator '%s' found unexpected type.";

    public IllegalTypeInComparisonException(String comparisonOperatorName) {
        super(String.format(EXCEPTION_TEXT_TEMPLATE, comparisonOperatorName));
    }
}
