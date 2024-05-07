package com.parch.combine.core.component.tools.sql;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.tools.compare.CompareGroupConfig;

/**
 * SQL片段配置项
 */
public class SqlItem extends CompareGroupConfig {

    @Field(key = "sql", name = "满足条件时会生效的SQL语句（段）", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldDesc("简单的SQL语句，注意：sql和sqlConfigs必须只配置一项")
    @FieldEg(eg = "select * from t_user where 1=1", desc = "要执行的SQL语句（段）")
    @FieldEg(eg = "and name = ${name}", desc = "要执行的SQL语句（段）")
    private String sql;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}