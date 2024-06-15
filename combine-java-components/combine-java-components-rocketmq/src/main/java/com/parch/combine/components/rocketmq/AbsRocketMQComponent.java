package com.parch.combine.components.rocketmq;

import com.parch.combine.core.component.base.AbsComponent;

/**
 * RocketMQ公共处理器
 */
public abstract class AbsRocketMQComponent<T extends RocketMQInitConfig, R extends RocketMQLogicConfig> extends AbsComponent<T, R> {

    /**
     * 构造器
     *
     * @param initConfigClass  初始化配置类Class对象
     * @param logicConfigClass 业务配置类Class对象
     */
    public AbsRocketMQComponent(Class<T> initConfigClass, Class<R> logicConfigClass) {
        super(initConfigClass, logicConfigClass);
    }
}
