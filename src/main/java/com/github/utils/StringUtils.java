package com.github.utils;

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
    }
}
