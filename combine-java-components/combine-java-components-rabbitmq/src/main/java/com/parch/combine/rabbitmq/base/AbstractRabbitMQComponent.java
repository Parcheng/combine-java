package com.parch.combine.rabbitmq.base;

import com.parch.combine.core.component.base.AbstractComponent;

/**
 * RocketMQ公共处理器
 */
public abstract class AbstractRabbitMQComponent<T extends RabbitMQInitConfig, R extends RabbitMQLogicConfig> extends AbstractComponent<T, R> {

    /**
     * 构造器
     *
     * @param initConfigClass  初始化配置类Class对象
     * @param logicConfigClass 业务配置类Class对象
     */
    public AbstractRabbitMQComponent(Class<T> initConfigClass, Class<R> logicConfigClass) {
        super(initConfigClass, logicConfigClass);
    }
}
