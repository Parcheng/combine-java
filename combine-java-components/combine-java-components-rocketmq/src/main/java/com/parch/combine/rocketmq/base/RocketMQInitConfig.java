package com.parch.combine.rocketmq.base;

import com.parch.combine.core.component.base.IInitConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface RocketMQInitConfig extends IInitConfig {

    @Field(key = "service", name = "服务地址", type = FieldTypeEnum.TEXT, isRequired = true)
    String service();
}
