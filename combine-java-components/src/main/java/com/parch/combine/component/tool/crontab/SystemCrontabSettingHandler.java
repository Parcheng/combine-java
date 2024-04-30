package com.parch.combine.component.tool.crontab;

import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

/**
 * 设置处理器
 */
public class SystemCrontabSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("crontab", "定时任务", false, SystemCrontabComponent.class);
        // builder.addDesc("模板组件");

        builder.addProperty(PropertyTypeEnum.INIT, "poolSize", FieldTypeEnum.NUMBER, "定时任务线程池大小",  false, false, "10");


        builder.addProperty(PropertyTypeEnum.LOGIC, "jobFlowKey", FieldTypeEnum.TEXT, "定时任务流程KEY",  false, false, "组件自动生成");
        builder.addProperty(PropertyTypeEnum.LOGIC, "startTime", FieldTypeEnum.TEXT, "首次执行时间",  false, false, "当前时间");
        builder.addProperty(PropertyTypeEnum.LOGIC, "initialDelay", FieldTypeEnum.OBJECT, "首次执行延迟毫秒数",  false, false, "0");
        builder.addProperty(PropertyTypeEnum.LOGIC, "period", FieldTypeEnum.NUMBER, "执行间隔（毫秒）",  true, false);
        builder.addProperty(PropertyTypeEnum.LOGIC, "maxPeriod", FieldTypeEnum.NUMBER, "最大周期（毫秒）",  false, false);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "maxPeriod", "如果设置了最大周期，则间隔周期会在这个区间中随机");
        builder.addProperty(PropertyTypeEnum.LOGIC, "fixedRate", FieldTypeEnum.BOOLEAN, "是否固定间隔",  false, false, "未设置随机周期默认为 false 否则 true");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "fixedRate", "如果设置了固定间隔，那么时间间隔是按任务开始执行时间算周期，否则按任务执行完成时间算周期");
        builder.addProperty(PropertyTypeEnum.LOGIC, "components", FieldTypeEnum.OBJECT, "要执行的组件集合（ID或组件配置）",  true, true);


        builder.setResult("异常信息或 true", false);
        return builder.get();
    }
}
