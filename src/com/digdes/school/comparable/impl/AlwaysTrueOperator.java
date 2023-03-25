package com.digdes.school.comparable.impl;

import com.digdes.school.comparable.ComparableOperator;
import com.digdes.school.exception.comparison.*;

import java.util.Map;

public class AlwaysTrueOperator implements ComparableOperator {
    @Override
    public boolean compare(Map<String, Object> row) {
        return true;
    }

    @Override
    public String getOperation() {
        return "alwaysTrue";
    }

    @Override
    public void InitialiseAndPerformChecks(Map<String, String> fieldsTypes) {}
}
