package com.parch.combine.gui.base.build.control.tree;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.build.GUIControlLogicConfig;
import com.parch.combine.gui.core.element.sub.GUISubElementConfig;
import com.parch.combine.gui.core.element.sub.SubElementLogicConfig;
import com.parch.combine.gui.core.event.EventConfig;

public interface GUIControlTreeLogicConfig extends GUIControlLogicConfig {

    @Field(key = "value", name = "默认选择", type = FieldTypeEnum.TEXT)
    @FieldObject(ItemConfig.class)
    String value();

    @Field(key = "items", name = "树配置", type = FieldTypeEnum.CONFIG, isArray = true, isRequired = true)
    @FieldObject(ItemConfig.class)
    ItemConfig[] items();

    @Field(key = "events", name = "事件配置集合", type = FieldTypeEnum.CONFIG, isArray = true)
    @FieldObject(EventConfig.class)
    EventConfig[] events();

    interface ItemConfig {

        @Field(key = "key", name = "KEY", type = FieldTypeEnum.TEXT, isRequired = true)
        String key();

        @Field(key = "text", name = "文本", type = FieldTypeEnum.TEXT, isRequired = true)
        String text();

        @Field(key = "children", name = "子树", type = FieldTypeEnum.CONFIG, isArray = true)
        @FieldObject(ItemConfig.class)
        ItemConfig[] children();
    }
}
