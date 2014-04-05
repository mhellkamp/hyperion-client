package com.dottydingo.hyperion.client.builder;

import com.dottydingo.hyperion.api.ApiObject;
import com.dottydingo.hyperion.client.HeaderFactory;
import com.dottydingo.hyperion.client.ParameterFactory;
import com.dottydingo.hyperion.client.Request;
import com.dottydingo.hyperion.client.RequestMethod;

import java.io.Serializable;

/**
 */
public class QueryRequestBuilder<T extends ApiObject<ID>,ID extends Serializable> extends RequestBuilder<T,ID>
{

    public QueryRequestBuilder(int version, Class<T> objectType)
    {
        super(version, objectType);
    }

    public QueryRequestBuilder<T, ID> returnFields(String... fields)
    {
        addParameter("fields",join(fields));
        return this;
    }

    public QueryRequestBuilder<T, ID> start(long start)
    {
        addParameter("start",Long.toString(start));
        return this;
    }

    public QueryRequestBuilder<T, ID> limit(long limit)
    {
        addParameter("start",Long.toString(limit));
        return this;
    }

    public QueryRequestBuilder<T, ID> withQuery(String query)
    {
        addParameter("query",query);
        return this;
    }

    public QueryRequestBuilder<T, ID> withSorts(String... sorts)
    {
        addParameter("sort",join(sorts));
        return this;
    }

    @Override
    public QueryRequestBuilder<T, ID> addParameter(String name, String value)
    {
        super.addParameter(name, value);
        return this;
    }

    @Override
    public QueryRequestBuilder<T, ID> addHeader(String name, String value)
    {
        super.addHeader(name, value);
        return this;
    }

    @Override
    public QueryRequestBuilder<T, ID> withHeaderFactory(HeaderFactory headerFactory)
    {
        super.withHeaderFactory(headerFactory);
        return this;
    }

    @Override
    public QueryRequestBuilder<T, ID> withParameterFactory(ParameterFactory parameterFactory)
    {
        super.withParameterFactory(parameterFactory);
        return this;
    }

    @Override
    public Request<T> build()
    {
        Request<T> request = super.build();
        request.setRequestMethod(RequestMethod.GET);
        return request;
    }
}
