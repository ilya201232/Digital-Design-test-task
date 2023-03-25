package com.digdes.school.comparable;

import com.digdes.school.exception.comparison.*;

import java.util.Map;

public interface ComparableOperator {

    boolean compare(Map<String, Object> row);

    String getOperation();

    void InitialiseAndPerformChecks(Map<String, String> fieldsTypes)
            throws IllegalTypeInComparisonException,
            NullValueInComparisonException, NoCollectionReferenceInComparisonException,
            MoreThanOneCollectionReferenceInComparisonException;
}
