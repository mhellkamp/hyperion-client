package com.dottydingo.hyperion.client.request;

import com.dottydingo.hyperion.api.ApiObject;
import com.dottydingo.hyperion.client.MultiMap;

/**
 */
public abstract class AbstractRequest<T extends ApiObject>
{
    private MultiMap headers;
    private MultiMap parameters;
    private Class<T> entityType;

    public MultiMap getHeaders()
    {
        return headers;
    }

    public void setHeaders(MultiMap headers)
    {
        this.headers = headers;
    }

    public MultiMap getParameters()
    {
        return parameters;
    }

    public void setParameters(MultiMap parameters)
    {
        this.parameters = parameters;
    }

    public abstract String getClientRequestMethod();

    public Class<T> getEntityType()
    {
        return entityType;
    }

    public void setEntityType(Class<T> entityType)
    {
        this.entityType = entityType;
    }

    public String getPath()
    {
        return "";
    }
}
