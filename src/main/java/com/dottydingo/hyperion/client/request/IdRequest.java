package com.dottydingo.hyperion.client.request;

import com.dottydingo.hyperion.api.ApiObject;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 */
public abstract class IdRequest<T extends ApiObject> extends AbstractRequest<T>
{
    private List<?> ids = Collections.emptyList();

    public void setIds(List<?> ids)
    {
        this.ids = ids;
    }

    @Override
    public String getPath()
    {
        if(ids.size() == 0)
            return"";

        StringBuilder sb = new StringBuilder();
        sb.append(ids.get(0));

        if(ids.size() == 1)
            return sb.toString();

        for (int i = 1; i < ids.size(); i++)
        {
            sb.append(",").append(ids.get(i));
        }

        return sb.toString();
    }
}
