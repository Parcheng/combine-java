package com.parch.combine.component.logic.loop;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.tools.compare.CompareGroupConfig;

import java.util.List;

/**
 * 逻辑配置类
 */
public class LogicLoopLogicConfig extends LogicConfig {

    /**
     * 要遍历的数据来源
     */
    private String source;

    /**
     * 遍历次数，默认为source数组的长度
     */
    private Integer count;

    /**
     * 规则参数
     */
    private List<Object> components;

    /**
     * 条件配置
     */
    private LoopConditionConfig condition;

    /**
     * 失败停止
     */
    private Boolean failStop;


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

    /**
     * 逻辑判断配置项
     */
    public static class LoopConditionConfig {
        /**
         * 跳过当前循环
         */
        private CompareGroupConfig skip;

        /**
         * 停止循环
         */
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
}
