package com.github.interceptors;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Properties;

public class MySqlDialect implements Dialect{

    @Override
    public boolean skip(String msId, Object parameterObject, RowBounds rowBounds) {
        if(rowBounds != RowBounds.DEFAULT){
            return false;
        }
        return true;
    }

    @Override
    public boolean beforeCount(String msId, Object parameterObject, RowBounds rowBounds) {
        if(rowBounds instanceof PageRowBounds){
            return true;
        }
        return false;
    }

    @Override
    public String getCountSql(BoundSql boundSql, Object parameterObject, RowBounds rowBounds, CacheKey cacheKey) {
        return "select count(*) from (" + boundSql.getSql() + ") temp";
    }

    @Override
    public void afterCount(long count, Object parameterObject, RowBounds rowBounds) {
        ((PageRowBounds)rowBounds).setTotal(count);
    }

    @Override
    public boolean beforePage(String msId, Object parameterObject, RowBounds rowBounds) {
        if(rowBounds != RowBounds.DEFAULT){
            return true;
        }
        return false;
    }

    @Override
    public String getPageSql(BoundSql boundSql, Object parameterObject, RowBounds rowBounds, CacheKey pageKey) {
        pageKey.update("RowBounds");
        return boundSql.getSql() + " limit " + rowBounds.getOffset() + "," + rowBounds.getLimit();
    }

    @Override
    public Object afterPage(List pageList, Object paramterObject, RowBounds rowBounds) {
        return pageList;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
