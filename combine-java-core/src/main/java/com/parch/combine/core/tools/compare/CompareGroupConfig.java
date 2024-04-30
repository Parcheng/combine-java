package com.parch.combine.core.tools.compare;

import java.util.List;

/**
 * 比较-组配置
 */
public class CompareGroupConfig {

    /**
     * 比较关系（AND且 | OR或）
     */
    String relation;

    /**
     * 条件集合
     */
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
