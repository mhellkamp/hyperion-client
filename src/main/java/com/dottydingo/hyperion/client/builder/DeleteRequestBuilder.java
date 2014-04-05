package com.dottydingo.hyperion.client.builder;

import com.dottydingo.hyperion.api.ApiObject;
import com.dottydingo.hyperion.client.HeaderFactory;
import com.dottydingo.hyperion.client.ParameterFactory;
import com.dottydingo.hyperion.client.Request;
import com.dottydingo.hyperion.client.RequestMethod;

import java.io.Serializable;

/**
 */
public class DeleteRequestBuilder<T extends ApiObject<ID>,ID extends Serializable> extends RequestBuilder<T,ID>
{
    private ID[] ids;

    public DeleteRequestBuilder(int version, Class<T> objectType, ID[] ids)
    {
        super(version, objectType);
        this.ids = ids;
    }

    @Override
    public DeleteRequestBuilder<T, ID> addParameter(String name, String value)
    {
        super.addParameter(name, value);
        return this;
    }

    @Override
    public DeleteRequestBuilder<T, ID> addHeader(String name, String value)
    {
        super.addHeader(name, value);
        return this;
    }

    @Override
    public DeleteRequestBuilder<T, ID> withHeaderFactory(HeaderFactory headerFactory)
    {
        super.withHeaderFactory(headerFactory);
        return this;
    }

    @Override
    public DeleteRequestBuilder<T, ID> withParameterFactory(ParameterFactory parameterFactory)
    {
        super.withParameterFactory(parameterFactory);
        return this;
    }

    @Override
    public Request<T> build()
    {
        Request<T> request = super.build();
        request.setRequestMethod(RequestMethod.DELETE);
        request.setPath(join(ids));
        return request;
    }
}
