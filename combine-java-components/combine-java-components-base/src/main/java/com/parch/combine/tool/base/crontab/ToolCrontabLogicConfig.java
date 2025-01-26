package com.parch.combine.tool.base.crontab;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldDesc;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface ToolCrontabLogicConfig extends ILogicConfig {

    @Field(key = "jobFlowKey", name = "定时任务流程KEY", type = FieldTypeEnum.TEXT, defaultValue = "组件自动生成")
    String jobFlowKey();

    @Field(key = "startTime", name = "首次执行时间", type = FieldTypeEnum.TEXT, defaultValue = "当前时间")
    String startTime();

    @Field(key = "initialDelay", name = "首次执行延迟毫秒数", type = FieldTypeEnum.NUMBER, defaultValue = "0")
    Integer initialDelay();

    @Field(key = "period", name = "执行间隔（毫秒）", type = FieldTypeEnum.NUMBER, parseExpression = false, isRequired = true)
    @FieldDesc("如果设置了固定间隔，那么时间间隔是按任务开始执行时间算周期，否则按任务执行完成时间算周期")
    Integer period();

    @Field(key = "maxPeriod", name = "最大周期（毫秒）", type = FieldTypeEnum.NUMBER, parseExpression = false)
    @FieldDesc("如果设置了最大周期，则间隔周期会在这个区间中随机")
    Integer maxPeriod();

    @Field(key = "components", name = "要执行的组件集合（ID或组件配置）", type = FieldTypeEnum.COMPONENT, isArray = true, isRequired = true)
    String[] components();
}
