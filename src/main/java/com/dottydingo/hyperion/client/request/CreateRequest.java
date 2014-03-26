package com.dottydingo.hyperion.client.request;

import com.dottydingo.hyperion.api.ApiObject;

/**
 */
public class CreateRequest<T extends ApiObject> extends BodyRequest<T>
{
    @Override
    public String getClientRequestMethod()
    {
        return "POST";
    }
}
