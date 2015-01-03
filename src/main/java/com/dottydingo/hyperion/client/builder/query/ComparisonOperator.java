package com.dottydingo.hyperion.client.builder.query;

/**
 */
public enum ComparisonOperator
{
    EQUAL("=="),
    NOT_EQUAL("!="),
    GREATER_THAN("=gt="),
    GREATER_THAN_OR_EQUAL("=ge="),
    LESS_THAN("=lt="),
    LESS_THAN_OR_EQUAL("=le=");

    private String value;

    ComparisonOperator(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
