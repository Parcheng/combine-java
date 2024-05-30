package com.parch.combine.components.access.rocketmq.consumer;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.components.access.rocketmq.RocketMQLogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.List;

public class RocketMQConsumerLogicConfig extends RocketMQLogicConfig {

    @Field(key = "listenFlowKey", name = "监听流程的KEY", type = {FieldTypeEnum.TEXT, FieldTypeEnum.EXPRESSION}, defaultValue = "组件自动生成")
    private String listenFlowKey;

    @Field(key = "consumerGroup", name = "消费者组", type = {FieldTypeEnum.TEXT, FieldTypeEnum.EXPRESSION}, defaultValue = "逻辑配置ID")
    private String consumerGroup;

    @Field(key = "expression", name = "表达式", type = {FieldTypeEnum.TEXT, FieldTypeEnum.EXPRESSION}, defaultValue = "*")
    private String expression;

    @Field(key = "components", name = "监听到消息后要执行的组件集合", type = FieldTypeEnum.COMPONENT, isArray = true, isRequired = true)
    private List<Object> components;

    public String getListenFlowKey() {
        return listenFlowKey;
    }

    public void setListenFlowKey(String listenFlowKey) {
        this.listenFlowKey = listenFlowKey;
    }

    public String getConsumerGroup() {
        return consumerGroup;
    }

    public void setConsumerGroup(String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public List<Object> getComponents() {
        return components;
    }

    public void setComponents(List<Object> components) {
        this.components = components;
    }

    @Override
    public void init() {
        if (getConsumerGroup() == null) {
            setConsumerGroup(getId());
        }
        if (CheckEmptyUtil.isEmpty(getExpression())) {
            setExpression("*");
        }
        if (CheckEmptyUtil.isEmpty(getListenFlowKey())) {
            setListenFlowKey(getId() + "-Listen");
        }
    }
}
