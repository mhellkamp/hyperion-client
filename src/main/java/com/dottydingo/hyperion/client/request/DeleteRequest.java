package com.dottydingo.hyperion.client.request;

import com.dottydingo.hyperion.api.ApiObject;

import java.io.Serializable;

/**
 */
public class DeleteRequest<T extends ApiObject> extends IdRequest<T>
{

    @Override
    public String getClientRequestMethod()
    {
        return "DELETE";
    }

}
