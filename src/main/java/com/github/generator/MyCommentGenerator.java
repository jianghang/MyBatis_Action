package com.github.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Properties;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

/**
 * Created by jianghang on 2018/4/30.
 */
public class MyCommentGenerator extends DefaultCommentGenerator{

    private boolean supppressAllComments;

    private boolean addRemarkComments;

    @Override
    public void addConfigurationProperties(Properties properties) {
        super.addConfigurationProperties(properties);

        supppressAllComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));

        addRemarkComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_ADD_REMARK_COMMENTS));
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if(supppressAllComments){
            return;
        }

        field.addJavaDocLine("/**");
        String remarks = introspectedColumn.getRemarks();

        if(addRemarkComments && StringUtility.stringHasValue(remarks)){
            String[] remarkLines = remarks.split(System.getProperty("line.separator"));
            for(String remarkLine : remarkLines){
                field.addJavaDocLine(" * " + remarkLine);
            }
        }

        field.addJavaDocLine(" * " + introspectedColumn.getActualColumnName());
        field.addJavaDocLine(" */");
    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if(supppressAllComments){
            return;
        }
    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if(supppressAllComments){
            return;
        }
    }
}
