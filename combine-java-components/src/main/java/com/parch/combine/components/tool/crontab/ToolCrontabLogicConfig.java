package com.parch.combine.components.tool.crontab;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.old.LogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.List;

/**
 * 逻辑配置类
 */
public class ToolCrontabLogicConfig extends ILogicConfig {

    @Field(key = "jobFlowKey", name = "定时任务流程KEY", type = FieldTypeEnum.TEXT, defaultValue = "组件自动生成")
    private String jobFlowKey;

    @Field(key = "startTime", name = "首次执行时间", type = FieldTypeEnum.TEXT, defaultValue = "当前时间")
    private String startTime;

    @Field(key = "initialDelay", name = "首次执行延迟毫秒数", type = FieldTypeEnum.NUMBER, defaultValue = "0")
    private Integer initialDelay;

    @Field(key = "period", name = "执行间隔（毫秒）", type = FieldTypeEnum.NUMBER, isRequired = true)
    private Integer period;

    @Field(key = "maxPeriod", name = "最大周期（毫秒）", type = FieldTypeEnum.NUMBER)
    @FieldDesc("如果设置了最大周期，则间隔周期会在这个区间中随机")
    private Integer maxPeriod;

    @Field(key = "fixedRate", name = "是否固定间隔", type = FieldTypeEnum.BOOLEAN, defaultValue = "如果设置了随机周期该值为 true 否则 false")
    @FieldDesc("如果设置了固定间隔，那么时间间隔是按任务开始执行时间算周期，否则按任务执行完成时间算周期")
    private Boolean fixedRate;

    @Field(key = "components", name = "要执行的组件集合（ID或组件配置）", type = FieldTypeEnum.COMPONENT, isArray = true, isRequired = true)
    private List<String> components;

    @Override
    public void init() {
        if (getInitialDelay() == null) {
            setInitialDelay(0);
        }
        if (CheckEmptyUtil.isEmpty(getJobFlowKey())) {
            setJobFlowKey(getId() + "-Listen");
        }
        if (getFixedRate() == null) {
            setFixedRate(getMaxPeriod() == null);
        }
    }

    public List<Object> getComponents() {
        return components;
    }

    public void setComponents(List<Object> components) {
        this.components = components;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getInitialDelay() {
        return initialDelay;
    }

    public void setInitialDelay(Integer initialDelay) {
        this.initialDelay = initialDelay;
    }

    public Integer getPeriod() {
        return period;
    }

    public Integer getMaxPeriod() {
        return maxPeriod;
    }

    public void setMaxPeriod(Integer maxPeriod) {
        this.maxPeriod = maxPeriod;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getJobFlowKey() {
        return jobFlowKey;
    }

    public void setJobFlowKey(String jobFlowKey) {
        this.jobFlowKey = jobFlowKey;
    }

    public Boolean getFixedRate() {
        return fixedRate;
    }

    public void setFixedRate(Boolean fixedRate) {
        this.fixedRate = fixedRate;
    }
}
