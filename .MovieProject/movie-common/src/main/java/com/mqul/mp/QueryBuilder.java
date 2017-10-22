package com.mqul.mp;


import javax.persistence.Query;

public class QueryBuilder {

    private StringBuilder sb;
    private final static String SELECT = "SELECT";
    private final static String COUNT = "COUNT";

    private static boolean first = true;

    public QueryBuilder(String query)
    {
        sb = new StringBuilder();
        sb.append(query);
    }

    public QueryBuilder where(String l, String r)
    {
        if(first)
        {
            sb.append(String.format(" WHERE %s = %s ", l, r));
            first = false;
        }

        sb.append(String.format(" AND %s = %s ", l, r));

        return this;
    }

    public String build()
    {
        return sb.toString();
    }
}