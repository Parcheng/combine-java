package com.parch.combine.components.access.rocketmq;

import com.parch.combine.core.component.base.InitConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public class RocketMQInitConfig extends InitConfig {

    @Field(key = "service", name = "服务地址", type = FieldTypeEnum.TEXT, isRequired = true)
    private String service;

    @Override
    public void init() {}

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
