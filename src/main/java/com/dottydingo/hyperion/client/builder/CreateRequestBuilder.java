package com.dottydingo.hyperion.client.builder;

import com.dottydingo.hyperion.api.ApiObject;
import com.dottydingo.hyperion.client.HyperionClient;
import com.dottydingo.hyperion.client.MultiMap;
import com.dottydingo.hyperion.client.request.CreateRequest;

/**
 */
public class CreateRequestBuilder<T extends ApiObject> extends AbstractRequestBuilder<T,CreateRequest<T>>
{
    private String[] fields;
    private T update;

    public CreateRequestBuilder(Class<T> objectType)
    {
        super(objectType);
    }

    public CreateRequestBuilder<T> forFields(String... fields)
    {
        this.fields = fields;
        return this;
    }

    public CreateRequestBuilder<T> create(T item)
    {
        this.update = item;
        return this;
    }

    @Override
    public CreateRequest<T> build()
    {
        MultiMap headers = resolveHeaders();
        MultiMap parameters = resolveParameters();

        if(version != null)
            parameters.add("version",version.toString());

        if(fields != null && fields.length > 0)
            parameters.add("fields",join(fields));

        CreateRequest<T> request = new CreateRequest<T>();
        request.setEntityType(objectType);
        request.setHeaders(headers);
        request.setParameters(parameters);

        request.setBody(update);

        return request;
    }

    public T execute(HyperionClient client)
    {
        return client.create(this.build());
    }
}
