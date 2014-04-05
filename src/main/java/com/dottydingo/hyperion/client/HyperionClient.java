package com.dottydingo.hyperion.client;

import com.dottydingo.hyperion.api.ApiObject;
import com.dottydingo.hyperion.api.DeleteResponse;
import com.dottydingo.hyperion.api.EntityResponse;
import com.dottydingo.hyperion.api.ErrorResponse;
import com.dottydingo.hyperion.client.exception.ClientConnectionException;
import com.dottydingo.hyperion.client.exception.ClientException;
import com.dottydingo.hyperion.client.exception.ClientMarshallingException;
import com.dottydingo.hyperion.exception.HyperionException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 */
public class HyperionClient
{
    private static final ObjectMapper DEFAULT_OBJECT_MAPPER = new ObjectMapperBuilder().getObjectMapper();
    protected String baseUrl;
    protected OkHttpClient client;
    protected ObjectMapper objectMapper = DEFAULT_OBJECT_MAPPER;
    protected ParameterFactory parameterFactory;
    protected HeaderFactory headerFactory;
    protected AuthorizationFactory authorizationFactory;

    public HyperionClient(String baseUrl)
    {
        this.baseUrl = baseUrl;
        client = new OkHttpClient();
        // do not pool connections
        client.setConnectionPool(new ConnectionPool(0,1000));
    }

    public HyperionClient(String baseUrl, AuthorizationFactory authorizationFactory)
    {
        this(baseUrl);
        this.authorizationFactory = authorizationFactory;
    }

    public void setParameterFactory(ParameterFactory parameterFactory)
    {
        this.parameterFactory = parameterFactory;
    }

    public void setHeaderFactory(HeaderFactory headerFactory)
    {
        this.headerFactory = headerFactory;
    }

    public void setObjectMapper(ObjectMapper objectMapper)
    {
        this.objectMapper = objectMapper;
    }

    public void setProxy(Proxy proxy)
    {
        java.net.Proxy p = new java.net.Proxy(java.net.Proxy.Type.HTTP,
                new InetSocketAddress(proxy.getHost(),proxy.getPort()));
        client.setProxy(p);
    }

    protected void setClient(OkHttpClient client)
    {
        this.client = client;
    }

    public <T extends ApiObject> EntityResponse<T> get(Request<T> request)
    {
        HttpURLConnection connection = executeRequest(request);
        return readResponse(connection, objectMapper.getTypeFactory()
                            .constructParametricType(EntityResponse.class, request.getEntityType()));
    }

    public <T extends ApiObject> EntityResponse<T> query(Request<T> request)
    {
        HttpURLConnection connection = executeRequest(request);
        return readResponse(connection, objectMapper.getTypeFactory()
                .constructParametricType(EntityResponse.class, request.getEntityType()));
    }

    public int delete(Request request)
    {
        HttpURLConnection connection = executeRequest(request);
        DeleteResponse response = readResponse(connection, objectMapper.getTypeFactory().constructType(
                DeleteResponse.class));
        return response.getCount();
    }

    public <T extends ApiObject> T create(Request<T> request)
    {
        HttpURLConnection connection = executeRequest(request);
        return readResponse(connection, objectMapper.getTypeFactory().constructType(request.getEntityType()));
    }

    public <T extends ApiObject> T update(Request<T> request)
    {
        HttpURLConnection connection = executeRequest(request);
        return readResponse(connection, objectMapper.getTypeFactory().constructType(request.getEntityType()));
    }

    protected <T> T readResponse(HttpURLConnection connection,JavaType javaType)
    {
        try
        {
            return objectMapper.readValue(connection.getInputStream(),javaType);
        }
        catch (IOException e)
        {
            throw new ClientMarshallingException("Error reading results.",e);
        }
        finally
        {
            connection.disconnect();
        }
    }

    protected HttpURLConnection executeRequest(Request request)
    {
        try
        {
            HttpURLConnection connection = client.open(URI.create(buildUrl(request)).toURL());
            executeRequest(request, connection);

            if(connection.getResponseCode() == HttpURLConnection.HTTP_UNAUTHORIZED && authorizationFactory != null
                    && authorizationFactory.retryOnAuthenticationError())
            {
                connection.disconnect();
                connection = client.open(URI.create(buildUrl(request)).toURL());
                executeRequest(request, connection);

            }
            if (connection.getResponseCode() >= HttpURLConnection.HTTP_BAD_REQUEST)
            {
                throw readException(connection);
            }

            return connection;
        }
        catch (IOException e)
        {
            throw new ClientConnectionException("Error calling service.",e);
        }

    }

    private void executeRequest(Request request, HttpURLConnection connection) throws IOException
    {
        setHeaders(connection, request);
        connection.setRequestMethod(request.getRequestMethod().name());

        if(request.getRequestMethod().isBodyRequest())
        {
            connection.addRequestProperty("Content-type","application/json");
            objectMapper.writeValue(connection.getOutputStream(),request.getRequestBody());
        }
    }

    protected HyperionException readException(HttpURLConnection connection) throws IOException
    {
        try
        {
            ErrorResponse errorResponse = null;
            try
            {
                errorResponse = objectMapper.readValue(connection.getErrorStream(), ErrorResponse.class);
            }
            catch (Exception ignore)
            {
            }

            HyperionException resolvedException = null;
            if (errorResponse != null)
            {
                try
                {
                    Class exceptionClass = Class.forName(errorResponse.getType());
                    resolvedException = (HyperionException) exceptionClass.getConstructor(String.class)
                            .newInstance(errorResponse.getMessage());
                }
                catch (Exception ignore)
                {
                }

                if (resolvedException == null)
                {
                    resolvedException =
                            new HyperionException(errorResponse.getStatusCode(), errorResponse.getMessage());
                }
            }

            if (resolvedException == null)
                resolvedException =
                        new HyperionException(connection.getResponseCode(), connection.getResponseMessage());

            return resolvedException;
        }
        finally
        {
            connection.disconnect();
        }

    }

    protected String buildUrl(Request request)
    {
        StringBuilder sb = new StringBuilder(512);
        sb.append(baseUrl).append("/").append(request.getEntityName()).append("/");
        if(request.getPath() != null)
            sb.append(request.getPath());

        String queryString = buildQueryString(request);
        if(queryString.length()>0)
        {
            sb.append("?").append(queryString);
        }

        return sb.toString();
    }

    protected String buildQueryString(Request request)
    {
        MultiMap resolvedParameters = null;
        if(parameterFactory != null)
        {
            resolvedParameters = parameterFactory.getParameters();
        }

        if(resolvedParameters != null)
            resolvedParameters = resolvedParameters.merge(request.getParameters());
        else
            resolvedParameters = request.getParameters();

        int ct = 0;
        StringBuilder sb = new StringBuilder(512);
        for (Map.Entry<String, List<String>> entry : resolvedParameters.entries())
        {
            for (String value : entry.getValue())
            {
                if(ct++ > 0)
                    sb.append("&");
                sb.append(encode(entry.getKey())).append("=").append(encode(value));
            }
        }

        return sb.toString();
    }

    protected void setHeaders(HttpURLConnection connection,Request request)
    {
        MultiMap resolvedHeaders = null;
        if(headerFactory != null)
            resolvedHeaders = headerFactory.getHeaders();

        if(resolvedHeaders != null)
            resolvedHeaders = resolvedHeaders.merge(request.getHeaders());
        else
            resolvedHeaders = request.getHeaders();

        for (Map.Entry<String, List<String>> entry : resolvedHeaders.entries())
        {
            for (String value : entry.getValue())
            {
                connection.addRequestProperty(entry.getKey(),value);
            }
        }
    }

    protected String encode(String value)
    {
        try
        {
            return URLEncoder.encode(value,"UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            throw new ClientException(500,"Error encoding parameter.",e);
        }
    }

    private static class ObjectMapperBuilder
    {
        private final ObjectMapper objectMapper;

        private ObjectMapperBuilder()
        {
            objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        }

        private ObjectMapper getObjectMapper()
        {
            return objectMapper;
        }
    }

}
