package com.parch.combine.components.access.rocketmq;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.error.ComponentErrorHandler;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<String> init() {
        List<String> errorMsg = new ArrayList<>();
        RocketMQInitConfig initConfig = getInitConfig();
        RocketMQLogicConfig logicConfig = getLogicConfig();

        if (CheckEmptyUtil.isEmpty(initConfig.getService())) {
            errorMsg.add(ComponentErrorHandler.buildCheckInitMsg(initConfig, "MQ服务地址为空"));
        }
        if (CheckEmptyUtil.isEmpty(logicConfig.getTopic())) {
            errorMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "Topic为空"));
        }

        errorMsg.addAll(initConfig());

        return errorMsg;
    }

    public abstract List<String> initConfig();
}
