package com.parch.combine.component.access.rocketmq.product;

import com.parch.combine.component.access.redis.RedisSettingsHandler;
import com.parch.combine.component.access.rocketmq.RocketMQSettingsHandler;
import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

/**
 * 设置处理器
 */
public class RocketMQProductSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("rocketmq.product", "RocketMQ生产者组件", true, RocketMQProductComponent.class);
//        builder.addDesc("");

        RocketMQSettingsHandler.build(builder);
        builder.addProperty(PropertyTypeEnum.LOGIC, "producerGroup", FieldTypeEnum.TEXT, "生产者组", true, false, "逻辑配置ID");
        builder.addProperty(PropertyTypeEnum.LOGIC, "tags", FieldTypeEnum.TEXT, "标签", false, false);
        builder.addProperty(PropertyTypeEnum.LOGIC, "content", FieldTypeEnum.OBJECT, "消息内容", true, false);


        builder.setResult("Message ID", false);
        return builder.get();
    }
}
