package com.dottydingo.hyperion.client.builder2;

import com.dottydingo.hyperion.api.ApiObject;
import com.dottydingo.hyperion.client.HyperionClient;

/**
 */
public class CreateRequest<T extends ApiObject<?>> extends Request<T>
{
    private Class<T> type;
    private Object item;

    public CreateRequest(HyperionClient client, Class<T> type, Object item)
    {
        super(client);
        this.type = type;
        this.item = item;
    }
}
