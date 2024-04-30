package com.parch.combine.components.access.mysql;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.settings.annotations.*;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.tools.sql.SqlItem;
import java.util.ArrayList;
import java.util.List;

public class MysqlLogicConfig extends LogicConfig {

    @ComponentField(key = "sqlType", name = "SQL类型", type = FieldTypeEnum.SELECT, isRequired = true)
    @ComponentFieldSelect(enumClass = SqlTypeEnum.class)
    @ComponentFieldEg(eg = "SELECT_ONE", desc = "查询一条数据")
    private String sqlType;

    @ComponentField(key = "sql", name = "SQL类型", type = FieldTypeEnum.TEXT)
    @ComponentFieldDesc("简单的SQL语句，注意：sql和sqlConfigs必须只配置一项")
    @ComponentFieldEg(eg = "select * from t_user limit 1", desc = "要直接执行SQL语句")
    private String sql;

    @ComponentField(key = "sqlConfigs", name = "SQL语句配置", type = FieldTypeEnum.OBJECT, isArray = true)
    @ComponentFieldObject(type = SqlItem.class)
    @ComponentFieldDesc({"复杂的SQL语句配置，最终执行时会将所有满足条件的SQL语句拼接到一起，注意：sql和sqlConfigs必须只配置一项",
            "提示：如果不配置条件则认为满足条件，SQL语句（段）会生效"})
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
