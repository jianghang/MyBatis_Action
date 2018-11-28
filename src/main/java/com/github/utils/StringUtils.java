package com.github.utils;

import com.google.common.base.Splitter;

import java.util.List;
import java.util.UUID;

/**
 * Created by jianghang on 2018/4/29.
 */
public class StringUtils {

    public static Boolean isNotEmpty(String str){
        return org.apache.commons.lang3.StringUtils.isNotEmpty(str);
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replace("-","").toLowerCase());
        String packageStr = "com.github.model.Country;com.github.simple";
        String str = "com.github.simple.Country";
        List<String> stringList = Splitter.on(";").splitToList(packageStr);
        String[] strs = new String[stringList.size()];
        boolean result = org.apache.commons.lang3.StringUtils.containsAny(str,stringList.toArray(strs));
        System.out.println(result);
    }
}
