package com.parch.combine.rabbitmq.base.publish;

import com.parch.combine.rabbitmq.base.RabbitMQLogicConfig;
import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;

import java.util.Map;

public interface RabbitMQPublishLogicConfig extends RabbitMQLogicConfig {

    @Field(key = "confirm", name = "是否发布确认", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    Boolean confirm();

    @Field(key = "content", name = "消息内容", type = FieldTypeEnum.MAP)
    Map<String, Object> content();
}
