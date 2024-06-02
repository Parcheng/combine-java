package com.parch.combine.components.data.enums.register;

import com.parch.combine.components.data.enums.EnumCacheHandler;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 逻辑配置类
 */
public interface DataEnumRegisterLogicConfig extends ILogicConfig {

    @Field(key = "key", name = "枚举KEY", type = FieldTypeEnum.TEXT, isRequired = true)
    String key();

    @Field(key = "items", name = "枚举项配置集合", type = FieldTypeEnum.CONFIG, isRequired = true, isArray = true)
    @FieldObject(EnumCacheHandler.EnumItem.class)
    EnumItem[] items();

    interface EnumItem {

        @Field(key = "code", name = "枚举项编码", type = FieldTypeEnum.TEXT, isRequired = true)
        String code();

        @Field(key = "name", name = "枚举项名称", type = FieldTypeEnum.TEXT, isRequired = true)
        String name();

        @Field(key = "desc", name = "枚举项描述", type = FieldTypeEnum.TEXT)
        String desc();
    }
}
