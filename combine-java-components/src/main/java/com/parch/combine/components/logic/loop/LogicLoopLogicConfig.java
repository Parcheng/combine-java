package com.parch.combine.components.logic.loop;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldDesc;
import com.parch.combine.core.settings.annotations.ComponentFieldEg;
import com.parch.combine.core.settings.annotations.ComponentFieldObject;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.tools.compare.CompareGroupConfig;

import java.util.List;

/**
 * 逻辑配置类
 */
public class LogicLoopLogicConfig extends LogicConfig {

    @ComponentField(key = "source", name = "要遍历的数据", type = FieldTypeEnum.TEXT)
    @ComponentFieldDesc("提示：source 和 count 至少要配置一项")
    @ComponentFieldEg(eg = "$r.data", desc = "遍历组件 ID 为 data 的执行结果")
    private String source;

    @ComponentField(key = "count", name = "要循环的次数", type = FieldTypeEnum.TEXT)
    @ComponentFieldDesc("提示：source 和 count 至少要配置一项")
    @ComponentFieldEg(eg = "5", desc = "只遍历前五条，如果数据来源不足五条，则将数据遍历完为止")
    private Integer count;

    @ComponentField(key = "condition", name = "停止循环的条件配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldObject(type = LoopConditionConfig.class)
    private LoopConditionConfig condition;

    @ComponentField(key = "components", name = "要循环执行的逻辑配置", type = FieldTypeEnum.OBJECT, isRequired = true, isArray = true)
    @ComponentFieldDesc("可以是组件ID，也可以是组件配置")
    @ComponentFieldEg(eg = "[\"loopTest1\", { \"type\": \"XXX\", ... }]", desc = "条件满足后，先执行 ID 为 loopTest1 的组件配置，再执行第二项类型为 XXX 的组件配置逻辑")
    private List<Object> components;

    @ComponentField(key = "failStop", name = "逻辑执行失败是否终止", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    @ComponentFieldEg(eg = "false", desc = "组件逻辑执行报错时，不停止循环，依然继续执行")
    private Boolean failStop;

    public static class LoopConditionConfig {

        @ComponentField(key = "skip", name = "跳过当前循环的条件配置", type = FieldTypeEnum.OBJECT)
        @ComponentFieldObject(type = CompareGroupConfig.class)
        private CompareGroupConfig skip;

        @ComponentField(key = "finish", name = "终止循环的条件配置", type = FieldTypeEnum.OBJECT)
        @ComponentFieldObject(type = CompareGroupConfig.class)
        private CompareGroupConfig finish;

        public CompareGroupConfig getSkip() {
            return skip;
        }

        public void setSkip(CompareGroupConfig skip) {
            this.skip = skip;
        }

        public CompareGroupConfig getFinish() {
            return finish;
        }

        public void setFinish(CompareGroupConfig finish) {
            this.finish = finish;
        }
    }

    @Override
    public void init() {
        if (getFailStop() == null) {
            setFailStop(true);
        }
    }

    public List<Object> getComponents() {
        return components;
    }

    public void setComponents(List<Object> components) {
        this.components = components;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public LoopConditionConfig getCondition() {
        return condition;
    }

    public void setCondition(LoopConditionConfig condition) {
        this.condition = condition;
    }

    public Boolean getFailStop() {
        return failStop;
    }

    public void setFailStop(Boolean failStop) {
        this.failStop = failStop;
    }
}
