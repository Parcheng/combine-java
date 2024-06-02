package com.parch.combine.components.access.rocketmq.consumer;

import com.parch.combine.components.access.rocketmq.RocketMQLogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.List;

public interface RocketMQConsumerLogicConfig extends RocketMQLogicConfig {

    @Field(key = "listenFlowKey", name = "监听流程的KEY", type = FieldTypeEnum.TEXT, defaultValue = "组件自动生成")
    String listenFlowKey();

    @Field(key = "consumerGroup", name = "消费者组", type = FieldTypeEnum.TEXT, defaultValue = "逻辑配置ID")
    String consumerGroup();

    @Field(key = "expression", name = "表达式", type = FieldTypeEnum.TEXT, defaultValue = "*")
    default String expression() {
        return "*";
    };

    @Field(key = "components", name = "监听到消息后要执行的组件集合", type = FieldTypeEnum.COMPONENT, isArray = true, isRequired = true)
    List<String> components();
}
