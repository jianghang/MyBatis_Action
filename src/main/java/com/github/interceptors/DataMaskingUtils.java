package com.github.interceptors;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class DataMaskingUtils {

    public static String rewriteSQL(String sql, String dbType) {
        List<SQLStatement> sqlStatementList = SQLUtils.parseStatements(sql, dbType);
        if(CollectionUtils.isEmpty(sqlStatementList)){
            throw new RuntimeException("获取sql报错");
        }
        SQLStatement sqlStatement = sqlStatementList.get(0);
        if(sqlStatement instanceof SQLSelectStatement){
            SQLSelect sqlSelect = ((SQLSelectStatement) sqlStatement).getSelect();
            SQLSelectQueryBlock sqlSelectQueryBlock = (SQLSelectQueryBlock) sqlSelect.getQuery();
            List<SQLSelectItem> sqlSelectItemList = sqlSelectQueryBlock.getSelectList();
            sqlSelectItemList.forEach(item -> {
                SQLExpr sqlExpr = item.getExpr();
                if(sqlExpr instanceof SQLPropertyExpr){
                    SQLPropertyExpr sqlPropertyExpr = (SQLPropertyExpr) sqlExpr;
                    if("user_name".equals(sqlPropertyExpr.getName())){
                        item.setAlias(sqlPropertyExpr.getName());
                        SQLIdentifierExpr identifierExpr = new SQLIdentifierExpr("substring(a.user_name,1,2)");
                        item.setExpr(identifierExpr);
                    }
                }else if(sqlExpr instanceof SQLIdentifierExpr){
                    SQLIdentifierExpr identifierExpr = (SQLIdentifierExpr) sqlExpr;
                    if("user_name".equals(identifierExpr.getName())){
                        item.setAlias(identifierExpr.getName());
                        identifierExpr.setName("substring(user_name,1,2)");
                    }
                }
            });
        }

        return sqlStatement.toString();
    }

    public static void main(String[] args) {
        String sql = "";
        rewriteSQL(sql,"mysql");
    }
}
