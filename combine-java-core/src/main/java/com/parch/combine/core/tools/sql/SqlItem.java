package com.parch.combine.core.tools.sql;

import com.parch.combine.core.tools.compare.CompareGroupConfig;

/**
 * SQL片段配置项
 */
public class SqlItem extends CompareGroupConfig {

    /**
     * SQL语句
     */
    private String sql;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}