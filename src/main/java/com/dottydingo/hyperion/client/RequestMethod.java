package com.dottydingo.hyperion.client;

/**
 */
public enum RequestMethod
{
    GET(false),
    PUT(true),
    POST(true),
    DELETE(false);

    private boolean bodyRequest;

    RequestMethod(boolean bodyRequest)
    {
        this.bodyRequest = bodyRequest;
    }

    public boolean isBodyRequest()
    {
        return bodyRequest;
    }
}
