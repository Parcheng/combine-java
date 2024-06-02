package com.parch.combine.components.access.rocketmq.product;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.components.access.rocketmq.RocketMQLogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface RocketMQProductLogicConfig extends RocketMQLogicConfig {

    @Field(key = "tags", name = "标签", type = FieldTypeEnum.TEXT)
    default String tags() {
        return CheckEmptyUtil.EMPTY;
    }

    @Field(key = "producerGroup", name = "生产者组", type = FieldTypeEnum.TEXT, defaultValue = "逻辑配置ID")
    String producerGroup();

    @Field(key = "content", name = "消息内容", type = FieldTypeEnum.ANY, isRequired = true)
    Object content();
}
