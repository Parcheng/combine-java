package com.parch.combine.component.access.mysql;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.tools.sql.SqlItem;
import java.util.ArrayList;
import java.util.List;

public class MysqlLogicConfig extends LogicConfig {

    private String sqlType;

    private List<SqlItem> sqlConfigs;

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    public List<SqlItem> getSqlConfigs() {
        return sqlConfigs;
    }

    public void setSql(Object sql) {
        SqlItem sqlItem = new SqlItem();
        sqlItem.setSql(sql.toString());
        this.sqlConfigs = new ArrayList<>();
        this.sqlConfigs.add(sqlItem);
    }

    public void setSqlConfig(List<SqlItem> sql) {
        this.sqlConfigs = sql;
    }
}
