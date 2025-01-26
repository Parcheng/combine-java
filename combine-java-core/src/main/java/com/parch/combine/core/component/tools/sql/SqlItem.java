package com.parch.combine.core.component.tools.sql;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldDesc;
import com.parch.combine.core.component.settings.annotations.FieldEg;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.tools.compare.CompareGroupConfig;

/**
 * SQL片段配置项
 */
public interface SqlItem {

    @Field(key = "sql", name = "满足条件时会生效的SQL语句（段）", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldDesc("简单的SQL语句，注意：sql和sqlConfigs必须只配置一项")
    @FieldEg(eg = "select * from t_user where 1=1", desc = "要执行的SQL语句（段）")
    @FieldEg(eg = "and name = ${name}", desc = "要执行的SQL语句（段）")
    String sql();

    @Field(key = "compare", name = "比较条件配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(CompareGroupConfig.class)
    CompareGroupConfig compare();
}