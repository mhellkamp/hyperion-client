package com.dottydingo.hyperion.client.request;

import com.dottydingo.hyperion.api.ApiObject;


/**
 */
public class QueryRequest<T extends ApiObject> extends AbstractRequest<T>
{
    @Override
    public String getClientRequestMethod()
    {
        return "GET";
    }
}
