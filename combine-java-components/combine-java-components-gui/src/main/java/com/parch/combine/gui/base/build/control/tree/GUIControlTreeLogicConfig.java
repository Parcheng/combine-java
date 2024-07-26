package com.parch.combine.gui.base.build.control.tree;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.build.GUIControlLogicConfig;

public interface GUIControlTreeLogicConfig extends GUIControlLogicConfig {

    @Field(key = "value", name = "值配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(PageValue.class)
    PageValue value();

    interface PageValue {

        @Field(key = "page", name = "当前页", type = FieldTypeEnum.NUMBER, defaultValue = "1")
        Integer page();

        @Field(key = "pageSize", name = "每页数据量", type = FieldTypeEnum.NUMBER, defaultValue = "10")
        Integer pageSize();

        @Field(key = "dataCount", name = "总数据量", type = FieldTypeEnum.NUMBER, defaultValue = "0")
        Long dataCount();
    }
}
