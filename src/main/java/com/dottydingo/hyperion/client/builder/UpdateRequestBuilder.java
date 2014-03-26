package com.dottydingo.hyperion.client.builder;

import com.dottydingo.hyperion.api.ApiObject;
import com.dottydingo.hyperion.client.MultiMap;
import com.dottydingo.hyperion.client.request.UpdateRequest;

import java.io.Serializable;

/**
 */
public class UpdateRequestBuilder<T extends ApiObject<ID>,ID extends Serializable> extends AbstractRequestBuilder<T,UpdateRequest<T>>
{
    private String[] fields;
    private T update;

    public UpdateRequestBuilder(Class<T> objectType)
    {
        super(objectType);
    }

    public UpdateRequestBuilder<T,ID> forFields(String... fields)
    {
        this.fields = fields;
        return this;
    }

    public UpdateRequestBuilder<T,ID> update(T item)
    {
        this.update = item;
        return this;
    }

    @Override
    public UpdateRequest<T> build()
    {
        MultiMap headers = resolveHeaders();
        MultiMap parameters = resolveParameters();

        if(version != null)
            parameters.add("version",version.toString());

        if(fields != null && fields.length > 0)
            parameters.add("fields",join(fields));

        UpdateRequest<T> request = new UpdateRequest<T>();
        request.setEntityType(objectType);
        request.setHeaders(headers);
        request.setParameters(parameters);

        request.setId(update.getId());
        request.setBody(update);

        return request;
    }


}
