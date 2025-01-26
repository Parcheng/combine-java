package com.parch.combine.core.component.tools.compare;

import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldDesc;
import com.parch.combine.core.component.settings.annotations.FieldEg;

import java.util.List;

/**
 * 比较-组配置
 */
public class CompareGroupConfig {

    @Field(key = "relation", name = "比较类型", type = FieldTypeEnum.TEXT, defaultValue = "AND")
    @FieldDesc("可选值 AND | OR")
    @FieldEg(eg = "OR", desc = "所有条件是或的关系，满足任意条件即可")
    @FieldEg(eg = "AND", desc = "所有条件是且的关系，必须满足所有条件才行")
    String relation;

    @Field(key = "conditions", name = "比较条件集合", type = FieldTypeEnum.TEXT, isArray = true)
    @FieldDesc("格式：比较条件左值 比较类型 比较条件右值")
    @FieldEg(eg = "[\"#{username} NO_NULL\"]", desc = "入参username字段为空，则满足该条件")
    List<CompareConfig> conditions;

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public List<CompareConfig> getConditions() {
        return conditions;
    }

    public void setConditions(List<String> conditions) {
        this.conditions = CompareConfig.buildList(conditions);
    }
}
