package com.parch.combine.components.logic.judgment;

import com.parch.combine.core.component.base.old.LogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.tools.compare.CompareGroupConfig;

import java.util.List;

/**
 * 逻辑配置类
 */
public class LogicJudgmentLogicConfig extends ILogicConfig {

    @Field(key = "items", name = "配置项集合", type = FieldTypeEnum.ANY, isRequired = true, isArray = true)
    @FieldObject(type = LogicJudgmentItem.class)
    private List<LogicJudgmentItem> items;

    @Override
    public void init() {}

    /**
     * 逻辑判断配置项
     */
    public static class LogicJudgmentItem extends CompareGroupConfig {

        @Field(key = "components", name = "要执行的逻辑，可以是组件ID，也可以是组件配置", type = FieldTypeEnum.COMPONENT, isRequired = true, isArray = true)
        @FieldEg(eg = "[\"logicJudgmentTestData\", { \"type\": \"XXX\", ... }", desc = "条件满足后，先执行 ID 为 logicJudgmentTestData 的组件配置，再执行第二项类型为 XXX 的组件配置逻辑")
        List<String> components;

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
