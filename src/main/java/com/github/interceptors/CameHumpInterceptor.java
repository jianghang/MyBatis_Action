package com.github.interceptors;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Statement;
import java.util.*;

/**
 * Created by jianghang on 2018/5/7.
 */
@SuppressWarnings("unchecked")
@Intercepts(@Signature(type = ResultSetHandler.class,method = "handleResultSets",args = {Statement.class}))
public class CameHumpInterceptor implements Interceptor{

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        List<Object> list = (List<Object>) invocation.proceed();
        for(Object obj : list){
            if(obj instanceof Map){
                processMap((Map)obj);
            }else{
                break;
            }
        }

        return list;
    }

    private void processMap(Map map) {
        Set<String> keySet = new HashSet<>(map.keySet());
        for(String key : keySet){
            if((key.charAt(0) >= 'A' && key.charAt(0) <= 'Z') || key.contains("_")){
                Object value = map.get(key);
                map.remove(key);
                map.put(underlineToCamelhump(key),value);
            }
        }
    }

    private Object underlineToCamelhump(String key) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean nextUpperCase = false;

        for(int i = 0;i < key.length();i++){
            char c = key.charAt(i);
            if(c == '_'){
                if(stringBuilder.length() > 0){
                    nextUpperCase = true;
                }
            }else{
                if(nextUpperCase){
                    stringBuilder.append(Character.toUpperCase(c));
                    nextUpperCase = false;
                }else {
                    stringBuilder.append(Character.toLowerCase(c));
                }
            }
        }

        return stringBuilder.toString();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
