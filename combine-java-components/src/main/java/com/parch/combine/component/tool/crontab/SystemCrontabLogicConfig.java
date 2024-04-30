package com.parch.combine.component.tool.crontab;

import com.parch.combine.core.base.LogicConfig;
import java.util.List;

/**
 * 逻辑配置类
 */
public class SystemCrontabLogicConfig extends LogicConfig {

    private String jobFlowKey;

    private String startTime;

    private Integer initialDelay;

    private Integer period;

    private Integer maxPeriod;

    private Boolean fixedRate;

    private List<Object> components;

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
