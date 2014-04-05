package com.dottydingo.hyperion.client.builder;

import com.dottydingo.hyperion.api.ApiObject;

import java.io.Serializable;

/**
 */
public class RequestFactory<T extends ApiObject<ID>,ID extends Serializable>
{
    private Class<T> type;
    private int version;

    public RequestFactory(Class<T> type, int version)
    {
        this.type = type;
        this.version = version;
    }

    public CreateRequestBuilder<T,ID> create(T item)
    {
        return new CreateRequestBuilder<T, ID>(version,type,item);
    }

    public DeleteRequestBuilder<T,ID> delete(ID... ids)
    {
        return new DeleteRequestBuilder<T, ID>(version,type,ids);
    }

    public QueryRequestBuilder<T,ID> query()
    {
        return new QueryRequestBuilder<T, ID>(version,type);
    }

    public GetRequestBuilder<T,ID> find(ID... ids)
    {
        return new GetRequestBuilder<T, ID>(version,type,ids);
    }

    public UpdateRequestBuilder<T,ID> update(T item)
    {
        return new UpdateRequestBuilder<T, ID>(version,type,item);
    }
}
