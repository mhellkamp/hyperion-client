package com.dottydingo.hyperion.client.builder;

import com.dottydingo.hyperion.api.ApiObject;

import java.io.Serializable;

/**
 */
public class RequestBuilderFactory<T extends ApiObject<ID>,ID extends Serializable>
{
    private Class<T> type;
    private Integer version;

    public RequestBuilderFactory(Class<T> type)
    {
        this.type = type;
    }

    public RequestBuilderFactory(Class<T> type, Integer version)
    {
        this.type = type;
        this.version = version;
    }

    public CreateRequestBuilder<T> createRequest()
    {
        CreateRequestBuilder<T> requestBuilder = new CreateRequestBuilder<T>(type);
        requestBuilder.forVersion(version);
        return requestBuilder;
    }

    public DeleteRequestBuilder<T,ID> deleteRequest()
    {
        DeleteRequestBuilder<T, ID> requestBuilder = new DeleteRequestBuilder<T, ID>(type);
        requestBuilder.forVersion(version);
        return requestBuilder;
    }

    public GetRequestBuilder<T,ID> getRequest()
    {
        GetRequestBuilder<T, ID> requestBuilder = new GetRequestBuilder<T, ID>(type);
        requestBuilder.forVersion(version);
        return requestBuilder;
    }

    public QueryRequestBuilder<T> queryRequest()
    {
        QueryRequestBuilder<T> requestBuilder = new QueryRequestBuilder<T>(type);
        requestBuilder.forVersion(version);
        return requestBuilder;
    }

    public UpdateRequestBuilder<T,ID> updateRequest()
    {
        UpdateRequestBuilder<T, ID> requestBuilder = new UpdateRequestBuilder<T, ID>(type);
        requestBuilder.forVersion(version);
        return requestBuilder;
    }
}
