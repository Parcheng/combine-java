package com.parch.combine.components.logic.judgment;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldEg;
import com.parch.combine.core.settings.annotations.ComponentFieldObject;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.tools.compare.CompareGroupConfig;

import java.util.List;

/**
 * 逻辑配置类
 */
public class LogicJudgmentLogicConfig extends LogicConfig {

    @ComponentField(key = "items", name = "配置项集合", type = FieldTypeEnum.OBJECT, isRequired = true, isArray = true)
    @ComponentFieldObject(type = LogicJudgmentItem.class)
    private List<LogicJudgmentItem> items;

    /**
     * 逻辑判断配置项
     */
    public static class LogicJudgmentItem extends CompareGroupConfig {

        @ComponentField(key = "components", name = "要执行的逻辑，可以是组件ID，也可以是组件配置", type = FieldTypeEnum.OBJECT, isRequired = true, isArray = true)
        @ComponentFieldEg(eg = "[\"logicJudgmentTestData\", { \"type\": \"XXX\", ... }", desc = "条件满足后，先执行 ID 为 logicJudgmentTestData 的组件配置，再执行第二项类型为 XXX 的组件配置逻辑")
        List<Object> components;

        public List<Object> getComponents() {
            return components;
        }

        public void setComponents(List<Object> components) {
            this.components = components;
        }
    }

    public List<LogicJudgmentItem> getItems() {
        return items;
    }

    public void setItems(List<LogicJudgmentItem> items) {
        this.items = items;
    }
}
