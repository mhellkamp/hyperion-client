package com.dottydingo.hyperion.client.builder;

import com.dottydingo.hyperion.api.ApiObject;
import com.dottydingo.hyperion.client.MultiMap;
import com.dottydingo.hyperion.client.request.QueryRequest;

import java.io.Serializable;

/**
 */
public class QueryRequestBuilder<T extends ApiObject<? extends Serializable>> extends AbstractRequestBuilder<T,QueryRequest<T>>
{
    private Long start;
    private Long limit;
    private String query;
    private String[] fields;
    private String[] sorts;

    public QueryRequestBuilder(Class<T> objectType)
    {
        super(objectType);
    }

    public QueryRequestBuilder<T> start(long start)
    {
        this.start = start;
        return this;
    }

    public QueryRequestBuilder<T> limit(long limit)
    {
        this.limit = limit;
        return this;
    }

    public QueryRequestBuilder<T> withQuery(String query)
    {
        this.query = query;
        return this;
    }

    public QueryRequestBuilder<T> forFields(String... fields)
    {
        this.fields = fields;
        return this;
    }

    public QueryRequestBuilder<T> withSorts(String... sorts)
    {
        this.sorts = sorts;
        return this;
    }

    @Override
    public QueryRequest<T> build()
    {
        MultiMap headers = resolveHeaders();
        MultiMap parameters = resolveParameters();

        if(version != null)
            parameters.add("version",version.toString());

        if(start != null)
            parameters.add("start",start.toString());

        if(limit != null)
            parameters.add("limit",limit.toString());

        if(fields != null && fields.length > 0)
            parameters.add("fields",join(fields));

        if(sorts != null && sorts.length > 0)
            parameters.add("sort",join(sorts));

        if(query != null && query.length() > 0)
            parameters.add("query",query);

        QueryRequest<T> request = new QueryRequest<T>();
        request.setEntityType(objectType);
        request.setHeaders(headers);
        request.setParameters(parameters);
        return request;
    }

}
