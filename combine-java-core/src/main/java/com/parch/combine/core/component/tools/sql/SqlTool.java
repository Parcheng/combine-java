package com.parch.combine.core.component.tools.sql;

import com.parch.combine.core.common.canstant.SymbolConstant;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.DataTypeIsUtil;
import com.parch.combine.core.common.util.MatcherUtil;
import com.parch.combine.core.common.util.PrintUtil;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.tools.compare.CompareTool;
import com.parch.combine.core.component.tools.variable.DataFindHandler;
import com.parch.combine.core.component.tools.variable.DataVariableHelper;
import com.parch.combine.core.component.tools.variable.TextExpressionHelper;

import java.util.Collection;
import java.util.List;

/**
 * SQL语句操作帮助类
 */
public class SqlTool {

    /**
     * 根据配置构建SQL语句
     *
     * @param sqlConfigs SQL配置集合
     * @return SQL语句
     */
    public static String buildSql(List<SqlItem> sqlConfigs) {
        if (CheckEmptyUtil.isEmpty(sqlConfigs)) {
            return null;
        }

        StringBuilder sql = new StringBuilder();
        for (SqlItem item : sqlConfigs) {
            if (CheckEmptyUtil.isEmpty(item.getConditions()) || CompareTool.isPass(item, false)) {
                sql.append(CheckEmptyUtil.SPACE).append(item.getSql());
            }
        }

        return sql.toString();
    }

    @SuppressWarnings("unchecked")
    public static String replaceByParam(String sql, List<Object> sqlParams) {

        String[] sqlArr = new String[]{sql};

        // #{...} 替换为 ? 标识符
        MatcherUtil.matcher(sqlArr[0], "\\$\\{(.*?)}", paramStr -> {
            if (paramStr.length() <= 3) {
                ComponentErrorHandler.print("SQL语句替换-参数值为空");
                return;
            }

            String paramFieldStr = paramStr.substring(2, paramStr.length() -1);
            Object value = DataFindHandler.find(paramFieldStr);
            if (value instanceof Collection) {
                StringBuilder valueItems = new StringBuilder();
                Collection<Object> collectionValue = (Collection<Object>) value;
                int i = 0;
                for (Object valueItem : collectionValue) {
                    sqlParams.add(valueItem);
                    valueItems.append(SymbolConstant.QUESTION_SIGN);
                    if (i != collectionValue.size() - 1) {
                        valueItems.append(SymbolConstant.COMMA);
                    }
                    i++;
                }
                sqlArr[0] = sqlArr[0].replace(paramStr, valueItems.toString());
            } else {
                sqlParams.add(value);
                sqlArr[0] = sqlArr[0].replace(paramStr, SymbolConstant.QUESTION_SIGN);
            }
        });

        // #{...} 直接替换数据
        MatcherUtil.matcher(sqlArr[0], "#\\{(.*?)}", paramStr -> {
            if (paramStr.length() <= 3) {
                ComponentErrorHandler.print("SQL语句替换-参数值为空");
                return;
            }

            String paramFieldStr = paramStr.substring(2, paramStr.length() -1);
            Object value = DataFindHandler.find(paramFieldStr);
            sqlArr[0] = sqlArr[0].replace(paramStr, value == null ? "null" : value.toString());
        });

        return sqlArr[0];
    }

    /**
     * 替换为count查询语句
     *
     * @param sql Sql语句
     * @param countAs count别名
     * @return 结果
     */
    public static String replaceCount(String sql, String countAs) {
        String lowerSql = sql.toLowerCase();
        int index = lowerSql.indexOf("from");
        if (index == -1) {
            PrintUtil.printError("SQL不合规");
            return sql;
        }

        return "select count(1) as " + countAs + " " + sql.substring(index, lowerSql.length());
    }

    public static String setLimit(String sql, String pageName, String pageSizeName) {
        Object pageObj = TextExpressionHelper.getObject(pageName);
        Object pageSizeObj = TextExpressionHelper.getObject(pageSizeName);

        // 解析参数
        long page = pageObj == null || !DataTypeIsUtil.isLong(pageObj.toString()) ? 0 : Long.parseLong(pageObj.toString());
        page = page < 1 ? 1 : page;
        long pageSize = pageSizeObj == null || !DataTypeIsUtil.isLong(pageSizeObj.toString()) ? 10 : Long.parseLong(pageSizeObj.toString());

        // 处理SQL的末尾分号
        sql = sql.trim();
        if (sql.indexOf(";") == sql.length() - 1) {
            sql = sql.substring(0, sql.length() - 1);
        }

        // 拼接SQL
        long start = (page - 1) * pageSize;
        long end = page * pageSize;
        return sql + " limit " + start + ", " + end + ";";
    }
}
