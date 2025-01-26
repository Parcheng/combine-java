package com.parch.combine.rabbitmq.base.subscribe;

import com.parch.combine.core.component.settings.annotations.FieldDesc;
import com.parch.combine.rabbitmq.base.RabbitMQLogicConfig;
import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;

public interface RabbitMQSubscribeLogicConfig extends RabbitMQLogicConfig {

    @Field(key = "listenFlowKey", name = "监听流程的KEY", type = FieldTypeEnum.TEXT)
    @FieldDesc("组件自动生成")
    String listenFlowKey();

    @Field(key = "count", name = "拉取消息数量", type = FieldTypeEnum.NUMBER, defaultValue = "5")
    Integer count();

    @Field(key = "components", name = "监听到消息后要执行的组件集合", type = FieldTypeEnum.COMPONENT, isArray = true, isRequired = true)
    String[] components();
}
