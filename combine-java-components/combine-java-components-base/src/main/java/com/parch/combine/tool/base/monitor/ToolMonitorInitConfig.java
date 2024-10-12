package com.parch.combine.tool.base.monitor;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.IInitConfig;

public interface ToolMonitorInitConfig extends IInitConfig {

    @Field(key = "keyConfig", name = "各监控指标KEY配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(KeyConfig.class)
    KeyConfig keyConfig();

    interface KeyConfig {

        @Field(key = "CPUUsage", name = "CPU使用情况", type = FieldTypeEnum.TEXT, defaultValue = "CPUUsage")
        String CPUUsage();

        @Field(key = "threadCount", name = "线程数", type = FieldTypeEnum.TEXT, defaultValue = "threadCount")
        String threadCount();

        @Field(key = "initHeapMemory", name = "初始堆内存", type = FieldTypeEnum.TEXT, defaultValue = "initHeapMemory")
        String initHeapMemory();

        @Field(key = "usedHeapMemory", name = "已使用堆内存", type = FieldTypeEnum.TEXT, defaultValue = "usedHeapMemory")
        String usedHeapMemory();

        @Field(key = "committedHeapMemory", name = "已申请堆内存", type = FieldTypeEnum.TEXT, defaultValue = "committedHeapMemory")
        String committedHeapMemory();

        @Field(key = "maxHeapMemory", name = "可申请最大堆内存", type = FieldTypeEnum.TEXT, defaultValue = "maxHeapMemory")
        String maxHeapMemory();

        @Field(key = "initNonHeapMemory", name = "初始非堆内存", type = FieldTypeEnum.TEXT, defaultValue = "initNonHeapMemory")
        String initNonHeapMemory();

        @Field(key = "usedNonHeapMemory", name = "已使用非堆内存", type = FieldTypeEnum.TEXT, defaultValue = "usedNonHeapMemory")
        String usedNonHeapMemory();

        @Field(key = "committedNonHeapMemory", name = "已申请非堆内存", type = FieldTypeEnum.TEXT, defaultValue = "committedNonHeapMemory")
        String committedNonHeapMemory();

        @Field(key = "maxNonHeapMemory", name = "可申请最大非堆内存", type = FieldTypeEnum.TEXT, defaultValue = "maxNonHeapMemory")
        String maxNonHeapMemory();
    }
}
