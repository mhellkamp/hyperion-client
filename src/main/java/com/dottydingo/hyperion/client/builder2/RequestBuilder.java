package com.dottydingo.hyperion.client.builder2;

import com.dottydingo.hyperion.api.ApiObject;
import com.dottydingo.hyperion.client.HyperionClient;

/**
 */
public class RequestBuilder<T extends ApiObject<?>>
{
    private Class<T> type;
    private HyperionClient client;

    public RequestBuilder(Class<T> type, HyperionClient client)
    {
        this.type = type;
        this.client = client;
    }

    public Request<T> create(T item)
    {
        return new CreateRequest<T>(client,type,item);
    }

}
