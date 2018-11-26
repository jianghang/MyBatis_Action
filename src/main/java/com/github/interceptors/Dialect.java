package com.github.interceptors;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Properties;

/**
 * Created by jianghang on 2018/5/7.
 */
public interface Dialect {

    /**
     * 跳过count和分页查询
     *
     * @param msId
     * @param parameterObject
     * @param rowBounds
     * @return
     */
    boolean skip(String msId, Object parameterObject, RowBounds rowBounds);

    /**
     * 执行分页前，返回true会进行count查询，返回false会继续下面的beforePage判断
     *
     * @param msId
     * @param parameterObject
     * @param rowBounds
     * @return
     */
    boolean beforeCount(String msId,Object parameterObject,RowBounds rowBounds);

    String getCountSql(BoundSql boundSql, Object parameterObject, RowBounds rowBounds, CacheKey cacheKey);

    void afterCount(long count,Object parameterObject,RowBounds rowBounds);

    boolean beforePage(String msId,Object parameterObject,RowBounds rowBounds);

    String getPageSql(BoundSql boundSql,Object parameterObject,RowBounds rowBounds,CacheKey cacheKey);

    Object afterPage(List pageList,Object paramterObject,RowBounds rowBounds);

    void setProperties(Properties properties);
}
