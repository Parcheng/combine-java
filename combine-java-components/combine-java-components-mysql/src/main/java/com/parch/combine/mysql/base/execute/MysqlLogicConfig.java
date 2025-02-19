package com.parch.combine.mysql.base.execute;

import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldDesc;
import com.parch.combine.core.component.settings.annotations.FieldEg;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.annotations.FieldSelect;
import com.parch.combine.core.component.tools.sql.SqlItem;

public interface MysqlLogicConfig extends ILogicConfig {

    @Field(key = "sqlType", name = "SQL类型", type = FieldTypeEnum.SELECT, isRequired = true)
    @FieldSelect(enumClass = SqlTypeEnum.class)
    @FieldEg(eg = "SELECT_ONE", desc = "查询一条数据")
    String sqlType();

    @Field(key = "sql", name = "SQL语句", type = FieldTypeEnum.TEXT, isArray = true)
    @FieldDesc("简单的SQL语句，注意：sql和sqlConfigs必须只配置一项")
    @FieldEg(eg = "select * from t_user limit 1", desc = "要直接执行SQL语句")
    String[] sql();

    @Field(key = "sqlConfigs", name = "SQL语句配置", type = FieldTypeEnum.CONFIG, isArray = true)
    @FieldObject(SqlItem.class)
    @FieldDesc({"复杂的SQL语句配置，最终执行时会将所有满足条件的SQL语句拼接到一起，注意：sql和sqlConfigs必须只配置一项",
            "提示：如果不配置条件则认为满足条件，SQL语句（段）会生效"})
    SqlItem[] sqlConfigs();
}
