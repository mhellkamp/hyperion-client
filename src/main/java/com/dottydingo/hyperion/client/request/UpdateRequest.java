package com.dottydingo.hyperion.client.request;

import com.dottydingo.hyperion.api.ApiObject;


/**
 */
public class UpdateRequest<T extends ApiObject> extends BodyRequest<T>
{
    private Object id;

    public void setId(Object id)
    {
        this.id = id;
    }

    @Override
    public String getClientRequestMethod()
    {
        return "PUT";
    }

    @Override
    public String getPath()
    {
        return id.toString();
    }
}
