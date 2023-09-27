package org.example;

public class ExpressionEvaluator {
    public Integer add(String expression) {
        Integer result = null;
        if ( expression == null | expression.trim().length() == 0 )
            result = 0;
        return result;
    }
}
