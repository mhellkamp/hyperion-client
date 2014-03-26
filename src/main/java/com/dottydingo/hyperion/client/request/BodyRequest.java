package com.dottydingo.hyperion.client.request;

import com.dottydingo.hyperion.api.ApiObject;

/**
 */
public abstract class BodyRequest<T extends ApiObject> extends AbstractRequest<T>
{
    private T body;

    public void setBody(T body)
    {
        this.body = body;
    }

    public T getBody()
    {
        return body;
    }
}
