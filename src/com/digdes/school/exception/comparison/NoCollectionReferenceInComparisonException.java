package com.digdes.school.exception.comparison;

public class NoCollectionReferenceInComparisonException extends RuntimeException {

    private final static String EXCEPTION_TEMPLATE = "Comparison operator '%s' didn't find any collection reference.";

    public NoCollectionReferenceInComparisonException(String subcommandName) {
        super(String.format(EXCEPTION_TEMPLATE, subcommandName));
    }

}
