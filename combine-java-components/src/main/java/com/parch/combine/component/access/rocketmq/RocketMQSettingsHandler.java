package com.parch.combine.component.access.rocketmq;

import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;


/**
 * Redis设置信息
 */
public class RocketMQSettingsHandler {

    public static void build(ComponentSettingBuilder builder) {
        builder.addProperty(PropertyTypeEnum.INIT, "service", FieldTypeEnum.TEXT, "服务地址",  true, false);
        builder.addProperty(PropertyTypeEnum.LOGIC, "topic", FieldTypeEnum.TEXT, "消息Topic",  true, false);
    }
}
