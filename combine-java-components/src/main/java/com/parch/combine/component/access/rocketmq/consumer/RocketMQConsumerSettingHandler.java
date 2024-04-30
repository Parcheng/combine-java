package com.parch.combine.component.access.rocketmq.consumer;

import com.parch.combine.component.access.rocketmq.RocketMQSettingsHandler;
import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

import java.util.List;

/**
 * 设置处理器
 */
public class RocketMQConsumerSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("rocketmq.consumer", "RocketMQ消费者组件", true, RocketMQConsumerComponent.class);
//        builder.addDesc("");

        RocketMQSettingsHandler.build(builder);
        builder.addProperty(PropertyTypeEnum.LOGIC, "listenFlowKey", FieldTypeEnum.TEXT, "监听流程的KEY",  false, false, "组件自动生成");
        builder.addProperty(PropertyTypeEnum.LOGIC, "consumerGroup", FieldTypeEnum.TEXT, "消费者组", true, false, "逻辑配置ID");
        builder.addProperty(PropertyTypeEnum.LOGIC, "expression", FieldTypeEnum.TEXT, "表达式", false, false, "*");
        builder.addProperty(PropertyTypeEnum.LOGIC, "components", FieldTypeEnum.OBJECT, "监听到消息后要执行的组件集合", true, true);

        builder.setResult("异常信息或 true", false);
        builder.addResultDesc("消息数据格式：{ msgId: ‘消息ID’, body: ‘消息内容’ }");
        return builder.get();
    }
}
