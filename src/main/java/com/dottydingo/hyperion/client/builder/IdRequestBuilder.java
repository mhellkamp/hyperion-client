package com.dottydingo.hyperion.client.builder;

import com.dottydingo.hyperion.api.ApiObject;
import com.dottydingo.hyperion.client.builder.AbstractRequestBuilder;
import com.dottydingo.hyperion.client.builder.GetRequestBuilder;
import com.dottydingo.hyperion.client.request.AbstractRequest;

import java.io.Serializable;

/**
 */
public abstract class IdRequestBuilder<T extends ApiObject<ID>, ID extends Serializable,R extends AbstractRequest<T>>
        extends AbstractRequestBuilder<T,R>
{
    protected ID[] ids;

    public IdRequestBuilder(Class<T> objectType)
    {
        super(objectType);
    }

    public IdRequestBuilder<T, ID, R> forIds(ID... ids)
    {
        this.ids = ids;
        return this;
    }
}
