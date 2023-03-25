package com.digdes.school.exception.comparison;

public class MoreThanOneCollectionReferenceInComparisonException extends RuntimeException {

    private static final String EXCEPTION_TEXT_TEMPLATE = "Comparison operator '%s' found more than one collection reference." +
            "Make sure you don't use field name as compare string.";

    public MoreThanOneCollectionReferenceInComparisonException(String comparisonOperatorName) {
        super(String.format(EXCEPTION_TEXT_TEMPLATE, comparisonOperatorName));
    }
}
