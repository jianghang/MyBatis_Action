package com.github.interceptors;

import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Intercepts(
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class,
                        RowBounds.class, ResultHandler.class}))
public class DataMaskingInterceptor implements Interceptor {

    private String[] packages;
    private String dbType;
    private Field additionalParametersField;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object parameterObject = args[1];
        String id = ms.getId();
        if (StringUtils.containsAny(id, packages)) {
            RowBounds rowBounds = (RowBounds) args[2];
            ResultHandler resultHandler = (ResultHandler) args[3];
            BoundSql boundSql = ms.getBoundSql(parameterObject);
            Executor executor = (Executor) invocation.getTarget();

            String sql = DataMaskingUtils.rewriteSQL(boundSql.getSql(), dbType);
            BoundSql rewriteBoundSql = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(), parameterObject);
            CacheKey cacheKey = executor.createCacheKey(ms, parameterObject, rowBounds, boundSql);
            Map<String, Object> additionalParameters = (Map<String, Object>) additionalParametersField.get(boundSql);
            for (String key : additionalParameters.keySet()) {
                rewriteBoundSql.setAdditionalParameter(key, additionalParameters.get(key));
            }
            return executor.query(ms,parameterObject,RowBounds.DEFAULT,resultHandler,cacheKey,rewriteBoundSql);
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        String packageStr = properties.getProperty("packages");
        if (StringUtils.isBlank(packageStr)) {
            throw new RuntimeException("请配置被拦截包名的参数:packages");
        }
        String dbType = properties.getProperty("dbType");
        if (StringUtils.isBlank(dbType)) {
            throw new RuntimeException("请配置数据库类型参数:dbType");
        }
        this.dbType = dbType;
        List<String> stringList = Splitter.on(";").splitToList(packageStr);
        packages = new String[stringList.size()];
        packages = stringList.toArray(packages);

        try {
            additionalParametersField = BoundSql.class.getDeclaredField("additionalParameters");
            additionalParametersField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
