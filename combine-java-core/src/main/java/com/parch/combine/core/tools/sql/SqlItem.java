package com.parch.combine.core.tools.sql;

import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldDesc;
import com.parch.combine.core.settings.annotations.ComponentFieldEg;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.tools.compare.CompareGroupConfig;

/**
 * SQL片段配置项
 */
public class SqlItem extends CompareGroupConfig {

    @ComponentField(key = "sql", name = "满足条件时会生效的SQL语句（段）", type = FieldTypeEnum.TEXT, isRequired = true)
    @ComponentFieldDesc("简单的SQL语句，注意：sql和sqlConfigs必须只配置一项")
    @ComponentFieldEg(eg = "select * from t_user where 1=1", desc = "要执行的SQL语句（段）")
    @ComponentFieldEg(eg = "and name = ${name}", desc = "要执行的SQL语句（段）")
    private String sql;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}