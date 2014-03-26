package com.dottydingo.hyperion.client.builder2;

import com.dottydingo.hyperion.api.ApiObject;
import com.dottydingo.hyperion.client.HyperionClient;
import com.dottydingo.hyperion.client.MultiMap;

/**
 */
public abstract class Request<T extends ApiObject<?>>
{
    protected HyperionClient client;
    protected MultiMap headers;
    protected MultiMap parameters;

    protected Request(HyperionClient client)
    {
        this.client = client;
    }


}
