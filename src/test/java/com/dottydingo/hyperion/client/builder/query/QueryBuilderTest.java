package com.dottydingo.hyperion.client.builder.query;

import org.junit.Test;

import static org.junit.Assert.*;

public class QueryBuilderTest
{
    private QueryBuilder qb = new QueryBuilder();


    @Test
    public void testEq_String() throws Exception
    {
        assertEquals("foo==bar",qb.eq("foo","bar").build());
        assertEquals("foo=='bar bar'",qb.eq("foo","bar bar").build());
    }

    @Test
    public void testEq_boolean() throws Exception
    {
        assertEquals("foo==true",qb.eq("foo",true).build());
    }

    @Test
    public void testEq_int() throws Exception
    {
        assertEquals("foo==1",qb.eq("foo",1).build());
    }

    @Test
    public void testEq_double() throws Exception
    {
        assertEquals("foo==1.1",qb.eq("foo",1.1).build());
    }

    @Test
    public void testEq_long() throws Exception
    {
        assertEquals("foo==1",qb.eq("foo",1L).build());
    }

    @Test
    public void testEq_float() throws Exception
    {
        assertEquals("foo==1.999",qb.eq("foo",1.999f).build());
    }

    @Test
    public void testNe_String() throws Exception
    {
        assertEquals("foo!=bar",qb.ne("foo", "bar").build());
        assertEquals("foo!='bar bar'",qb.ne("foo", "bar bar").build());
    }

    @Test
    public void testNq_boolean() throws Exception
    {
        assertEquals("foo!=true",qb.ne("foo", true).build());
    }

    @Test
    public void testNe_int() throws Exception
    {
        assertEquals("foo!=1",qb.ne("foo", 1).build());
    }

    @Test
    public void testNe_double() throws Exception
    {
        assertEquals("foo!=1.1",qb.ne("foo", 1.1).build());
    }

    @Test
    public void testNe_long() throws Exception
    {
        assertEquals("foo!=1",qb.ne("foo", 1L).build());
    }

    @Test
    public void testNe_float() throws Exception
    {
        assertEquals("foo!=1.999",qb.ne("foo",1.999f).build());
    }

    @Test
    public void testGt_String() throws Exception
    {
        assertEquals("foo=gt=bar",qb.gt("foo", "bar").build());
        assertEquals("foo=gt='bar bar'",qb.gt("foo", "bar bar").build());
    }


    @Test
    public void testGt_int() throws Exception
    {
        assertEquals("foo=gt=1",qb.gt("foo", 1).build());
    }

    @Test
    public void testGt_double() throws Exception
    {
        assertEquals("foo=gt=1.1",qb.gt("foo", 1.1).build());
    }

    @Test
    public void testGt_long() throws Exception
    {
        assertEquals("foo=gt=1",qb.gt("foo", 1L).build());
    }

    @Test
    public void testGt_float() throws Exception
    {
        assertEquals("foo=gt=1.999",qb.gt("foo", 1.999f).build());
    }

    @Test
    public void testLt_String() throws Exception
    {
        assertEquals("foo=lt=bar",qb.lt("foo", "bar").build());
        assertEquals("foo=lt='bar bar'",qb.lt("foo", "bar bar").build());
    }


    @Test
    public void testLt_int() throws Exception
    {
        assertEquals("foo=lt=1",qb.lt("foo", 1).build());
    }

    @Test
    public void testLt_double() throws Exception
    {
        assertEquals("foo=lt=1.1",qb.lt("foo", 1.1).build());
    }

    @Test
    public void testLt_long() throws Exception
    {
        assertEquals("foo=lt=1",qb.lt("foo", 1L).build());
    }

    @Test
    public void testLt_float() throws Exception
    {
        assertEquals("foo=lt=1.999",qb.lt("foo", 1.999f).build());
    }

    @Test
    public void testGe_String() throws Exception
    {
        assertEquals("foo=ge=bar",qb.ge("foo", "bar").build());
        assertEquals("foo=ge='bar bar'",qb.ge("foo", "bar bar").build());
    }


    @Test
    public void testGe_int() throws Exception
    {
        assertEquals("foo=ge=1",qb.ge("foo", 1).build());
    }

    @Test
    public void testGe_double() throws Exception
    {
        assertEquals("foo=ge=1.1",qb.ge("foo", 1.1).build());
    }

    @Test
    public void testGe_long() throws Exception
    {
        assertEquals("foo=ge=1",qb.ge("foo", 1L).build());
    }

    @Test
    public void testGe_float() throws Exception
    {
        assertEquals("foo=ge=1.999",qb.ge("foo", 1.999f).build());
    }

    @Test
    public void testLe_String() throws Exception
    {
        assertEquals("foo=le=bar",qb.le("foo", "bar").build());
        assertEquals("foo=le='bar bar'",qb.le("foo", "bar bar").build());
    }


    @Test
    public void testLe_int() throws Exception
    {
        assertEquals("foo=le=1",qb.le("foo", 1).build());
    }

    @Test
    public void testLe_double() throws Exception
    {
        assertEquals("foo=le=1.1",qb.le("foo", 1.1).build());
    }

    @Test
    public void testLe_long() throws Exception
    {
        assertEquals("foo=le=1",qb.le("foo", 1L).build());
    }

    @Test
    public void testLe_float() throws Exception
    {
        assertEquals("foo=le=1.999",qb.le("foo", 1.999f).build());
    }

    @Test
    public void testAnd() throws Exception
    {
        assertEquals("foo==bar;baz==buzz",
                qb.and(
                        qb.eq("foo","bar"),
                        qb.eq("baz","buzz")
                ).build());
    }

    @Test
    public void testOr() throws Exception
    {
        assertEquals("foo==bar,baz==buzz",
                qb.or(
                        qb.eq("foo","bar"),
                        qb.eq("baz","buzz")
                ).build());
    }
}