package com.dottydingo.hyperion.client.builder;

import com.dottydingo.hyperion.api.ApiObject;
import com.dottydingo.hyperion.client.HeaderFactory;
import com.dottydingo.hyperion.client.ParameterFactory;
import com.dottydingo.hyperion.client.Request;
import com.dottydingo.hyperion.client.RequestMethod;

import java.io.Serializable;

/**
 */
public class UpdateRequestBuilder<T extends ApiObject<ID>,ID extends Serializable> extends RequestBuilder<T,ID>
{
    private T body;

    public UpdateRequestBuilder(int version, Class<T> objectType, T body)
    {
        super(version, objectType);
        this.body = body;
    }

    public UpdateRequestBuilder<T, ID> returnFields(String... fields)
    {
        addParameter("fields",join(fields));
        return this;
    }

    @Override
    public UpdateRequestBuilder<T, ID> addParameter(String name, String value)
    {
        super.addParameter(name, value);
        return this;
    }

    @Override
    public UpdateRequestBuilder<T, ID> addHeader(String name, String value)
    {
        super.addHeader(name, value);
        return this;
    }

    @Override
    public UpdateRequestBuilder<T, ID> withHeaderFactory(HeaderFactory headerFactory)
    {
        super.withHeaderFactory(headerFactory);
        return this;
    }

    @Override
    public UpdateRequestBuilder<T, ID> withParameterFactory(ParameterFactory parameterFactory)
    {
        super.withParameterFactory(parameterFactory);
        return this;
    }

    @Override
    public Request<T> build()
    {
        Request<T> request = super.build();
        request.setRequestBody(body);
        request.setRequestMethod(RequestMethod.POST);
        request.setPath(body.getId().toString());
        return request;
    }
}
