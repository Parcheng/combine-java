package com.parch.combine.html.base;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.html.core.canstant.ConfigFiledConstant;

/**
 * 数据加载配置
 */
public interface Config {

    @Field(key = ConfigFiledConstant.ID, name = "ID", type = FieldTypeEnum.TEXT, isRequired = true)
    String id();
}
