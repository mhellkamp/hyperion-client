package com.dottydingo.hyperion.client.builder;

import com.dottydingo.hyperion.api.ApiObject;
import com.dottydingo.hyperion.client.HeaderFactory;
import com.dottydingo.hyperion.client.MultiMap;
import com.dottydingo.hyperion.client.ParameterFactory;
import com.dottydingo.hyperion.client.request.AbstractRequest;

/**
 */
public abstract class AbstractRequestBuilder<T extends ApiObject,R extends AbstractRequest<T>>
{
    private MultiMap headers = new MultiMap();
    private MultiMap parameters = new MultiMap();
    private HeaderFactory headerFactory;
    private ParameterFactory parameterFactory;
    protected Integer version;
    protected Class<T> objectType;

    protected AbstractRequestBuilder(Class<T> objectType)
    {
        this.objectType = objectType;
    }

    public void setHeaderFactory(HeaderFactory headerFactory)
    {
        this.headerFactory = headerFactory;
    }

    public void setParameterFactory(ParameterFactory parameterFactory)
    {
        this.parameterFactory = parameterFactory;
    }

    public AbstractRequestBuilder<T,R> forVersion(int version)
    {
        this.version = version;
        return this;
    }

    public AbstractRequestBuilder<T,R> addParameter(String name,String value)
    {
        parameters.add(name,value);
        return this;
    }

    public AbstractRequestBuilder<T,R> addHeader(String name,String value)
    {
        headers.add(name,value);
        return this;
    }

    public abstract R build();

    protected MultiMap resolveHeaders()
    {
        MultiMap resolved = null;
        if(headerFactory != null)
            resolved = headerFactory.getHeaders();

        if(resolved != null)
            resolved = resolved.merge(headers);
        else
            resolved = headers;

        return resolved;
    }

    protected MultiMap resolveParameters()
    {
        MultiMap resolved = null;
        if(parameterFactory != null)
            resolved = parameterFactory.getParameters();

        if(resolved != null)
            resolved = resolved.merge(parameters);
        else
            resolved = parameters;

        return resolved;
    }

    protected String join(String[] values)
    {
        StringBuilder sb = new StringBuilder(100);

        sb.append(values.length);

        if(values.length == 1)
            return sb.toString();

        for (int i = 1; i < values.length; i++)
        {
            sb.append(",").append(values[i]);
        }

        return sb.toString();
    }
}
