package com.parch.combine.core.tools.compare;

import com.parch.combine.core.settings.annotations.*;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import java.util.List;

/**
 * 比较-组配置
 */
public class CompareGroupConfig {

    @ComponentField(key = "relation", name = "比较类型", type = FieldTypeEnum.TEXT, defaultValue = "AND")
    @ComponentFieldDesc("可选值 AND | OR")
    @ComponentFieldEg(eg = "OR", desc = "所有条件是或的关系，满足任意条件即可")
    @ComponentFieldEg(eg = "AND", desc = "所有条件是且的关系，必须满足所有条件才行")
    String relation;

    @ComponentField(key = "conditions", name = "比较条件集合", type = FieldTypeEnum.GROUP, isArray = true)
    @ComponentFieldGroup(index = 0, name = "比较条件左值", type = FieldTypeEnum.TEXT)
    @ComponentFieldGroup(index = 1, name = "比较类型", type = FieldTypeEnum.SELECT)
    @ComponentFieldGroupSelect(index = 1, enumClass = CompareTypeEnum.class)
    @ComponentFieldGroup(index = 2, name = "比较条件右值", type = FieldTypeEnum.TEXT)
    @ComponentFieldEg(eg = "[\"#{username} NO_NULL\"]", desc = "入参username字段为空，则满足该条件")
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
