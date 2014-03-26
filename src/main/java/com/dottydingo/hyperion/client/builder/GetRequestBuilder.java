package com.dottydingo.hyperion.client.builder;

import com.dottydingo.hyperion.api.ApiObject;
import com.dottydingo.hyperion.client.MultiMap;
import com.dottydingo.hyperion.client.request.GetRequest;

import java.io.Serializable;
import java.util.Arrays;

/**
 */
public class GetRequestBuilder<T extends ApiObject<ID>,ID extends Serializable> extends IdRequestBuilder<T,ID,GetRequest<T>>
{
    private String[] fields;

    public GetRequestBuilder(Class<T> objectType)
    {
        super(objectType);
    }

    public GetRequestBuilder<T,ID> forFields(String... fields)
    {
        this.fields = fields;
        return this;
    }

    @Override
    public GetRequest<T> build()
    {
        MultiMap headers = resolveHeaders();
        MultiMap parameters = resolveParameters();

        if(version != null)
            parameters.add("version",version.toString());

        if(fields != null && fields.length > 0)
            parameters.add("fields",join(fields));

        GetRequest<T> request = new GetRequest<T>();
        request.setEntityType(objectType);
        request.setHeaders(headers);
        request.setParameters(parameters);

        if(ids.length > 0)
            request.setIds(Arrays.asList(ids));

        return request;
    }


}
