package com.dottydingo.hyperion.client.request;

import com.dottydingo.hyperion.api.ApiObject;


/**
 */
public class GetRequest<T extends ApiObject> extends IdRequest<T>
{

    @Override
    public String getClientRequestMethod()
    {
        return "GET";
    }

}
