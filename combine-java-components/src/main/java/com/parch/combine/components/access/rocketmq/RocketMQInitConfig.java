package com.parch.combine.components.access.rocketmq;

import com.parch.combine.core.base.InitConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.config.FieldTypeEnum;

public class RocketMQInitConfig extends InitConfig {

    @ComponentField(key = "service", name = "服务地址", type = FieldTypeEnum.TEXT, isRequired = true)
    private String service;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
