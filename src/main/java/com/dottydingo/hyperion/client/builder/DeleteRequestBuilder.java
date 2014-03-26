package com.dottydingo.hyperion.client.builder;

import com.dottydingo.hyperion.api.ApiObject;
import com.dottydingo.hyperion.api.DeleteResponse;
import com.dottydingo.hyperion.client.HyperionClient;
import com.dottydingo.hyperion.client.MultiMap;
import com.dottydingo.hyperion.client.request.DeleteRequest;

import java.io.Serializable;
import java.util.Arrays;

/**
 */
public class DeleteRequestBuilder<T extends ApiObject<ID>,ID extends Serializable> extends IdRequestBuilder<T,ID,DeleteRequest<T>>
{

    public DeleteRequestBuilder(Class<T> objectType)
    {
        super(objectType);
    }

    @Override
    public DeleteRequest<T> build()
    {
        MultiMap headers = resolveHeaders();
        MultiMap parameters = resolveParameters();

        if(version != null)
            parameters.add("version",version.toString());

        DeleteRequest<T> request = new DeleteRequest<T>();
        request.setEntityType(objectType);
        request.setHeaders(headers);
        request.setParameters(parameters);

        if(ids.length > 0)
            request.setIds(Arrays.asList(ids));

        return request;
    }

    public int execute(HyperionClient client)
    {
        return client.delete(this.build());
    }
}
