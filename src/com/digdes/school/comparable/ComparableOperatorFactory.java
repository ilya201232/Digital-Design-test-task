package com.digdes.school.comparable;

import com.digdes.school.comparable.impl.*;
import com.digdes.school.constant.ComparableOperatorName;


public class ComparableOperatorFactory {
    public ComparableOperator createOperator(String type, Object first, Object second) throws IllegalArgumentException {
        return switch (type) {
            case ComparableOperatorName.EQUALS -> new EqualsOperator(first, second);
            case ComparableOperatorName.NOT_EQUALS -> new NotEqualsOperator(first, second);
            case ComparableOperatorName.GREATER -> new GreaterOperator(first, second);
            case ComparableOperatorName.GREATER_OR_EQUAL -> new GreaterOrEqualOperator(first, second);
            case ComparableOperatorName.LESS -> new LessOperator(first, second);
            case ComparableOperatorName.LESS_OR_EQUAL -> new LessOrEqualOperator(first, second);
            case ComparableOperatorName.LIKE -> new LikeOperator(first, second);
            case ComparableOperatorName.ILIKE -> new IlikeOperator(first, second);
            case ComparableOperatorName.AND -> new AndOperator(first, second);
            case ComparableOperatorName.OR -> new OrOperator(first, second);
            default -> throw new IllegalArgumentException();
        };
    }
}
