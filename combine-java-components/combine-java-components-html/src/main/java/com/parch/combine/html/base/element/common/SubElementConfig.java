package com.parch.combine.html.base.element.common;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 下级元素配置
 */
public interface SubElementConfig {

    @Field(key = "text", name = "文本内容", type = FieldTypeEnum.TEXT)
    String text();

    @Field(key = "elements", name = "元素组配置集合", type = FieldTypeEnum.COMPONENT, isArray = true)
    String[] elements();
}
