package com.dottydingo.hyperion.client.builder;

import com.dottydingo.hyperion.api.ApiObject;
import com.dottydingo.hyperion.client.HeaderFactory;
import com.dottydingo.hyperion.client.ParameterFactory;
import com.dottydingo.hyperion.client.Request;
import com.dottydingo.hyperion.client.RequestMethod;

import java.io.Serializable;

/**
 */
public class GetRequestBuilder<T extends ApiObject<ID>,ID extends Serializable> extends RequestBuilder<T,ID>
{
    private ID[] ids;

    public GetRequestBuilder(int version, Class<T> objectType, ID[] ids)
    {
        super(version, objectType);
        this.ids = ids;
    }

    public GetRequestBuilder<T, ID> returnFields(String... fields)
    {
        addParameter("fields",join(fields));
        return this;
    }

    @Override
    public GetRequestBuilder<T, ID> addParameter(String name, String value)
    {
        super.addParameter(name, value);
        return this;
    }

    @Override
    public GetRequestBuilder<T, ID> addHeader(String name, String value)
    {
        super.addHeader(name, value);
        return this;
    }

    @Override
    public GetRequestBuilder<T, ID> withHeaderFactory(HeaderFactory headerFactory)
    {
        super.withHeaderFactory(headerFactory);
        return this;
    }

    @Override
    public GetRequestBuilder<T, ID> withParameterFactory(ParameterFactory parameterFactory)
    {
        super.withParameterFactory(parameterFactory);
        return this;
    }

    @Override
    public Request<T> build()
    {
        Request<T> request = super.build();
        request.setPath(join(ids));
        request.setRequestMethod(RequestMethod.GET);
        return request;
    }
}
