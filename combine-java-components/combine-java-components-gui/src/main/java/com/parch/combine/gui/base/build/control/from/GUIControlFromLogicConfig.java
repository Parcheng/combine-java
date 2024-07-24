package com.parch.combine.gui.base.build.control.from;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.build.GUIControlLogicConfig;
import com.parch.combine.gui.base.build.control.buttons.GUIControlButtonGroupLogicConfig;
import com.parch.combine.gui.core.event.EventConfig;

public interface GUIControlFromLogicConfig extends GUIControlLogicConfig {

    @Field(key = "layout", name = "布局", type = FieldTypeEnum.SELECT, defaultValue = "HORIZONTAL")
    @FieldSelect(enumClass = LayoutEnum.class)
    String layout();

    @Field(key = "rate", name = "控件整体宽度占比（0-1）", type = FieldTypeEnum.NUMBER, defaultValue = "1")
    Float rate();

    @Field(key = "rate", name = "控件标签宽度占比（0-1）", type = FieldTypeEnum.NUMBER, defaultValue = "0")
    Float labelRate();

    @Field(key = "rate", name = "控件元素宽度占比（0-1）", type = FieldTypeEnum.NUMBER, defaultValue = "0")
    Float elementRate();

    @Field(key = "items", name = "FROM控件配置集合", type = FieldTypeEnum.CONFIG, isArray = true)
    @FieldObject(ItemConfig.class)
    ItemConfig[] items();

    interface ItemConfig {

        @Field(key = "key", name = "KEY", type = FieldTypeEnum.TEXT)
        String key();

        @Field(key = "label", name = "标签文本", type = FieldTypeEnum.TEXT)
        String label();

        @Field(key = "elementId", name = "元素ID", type = FieldTypeEnum.TEXT)
        String elementId();

        @Field(key = "defaultValue", name = "默认值", type = FieldTypeEnum.ANY)
        Object defaultValue();

        @Field(key = "rate", name = "整体宽度占比（0-1）", type = FieldTypeEnum.NUMBER, defaultValue = "1")
        Float rate();

        @Field(key = "rate", name = "标签宽度占比（0-1）", type = FieldTypeEnum.NUMBER, defaultValue = "0")
        Float labelRate();

        @Field(key = "rate", name = "元素宽度占比（0-1）", type = FieldTypeEnum.NUMBER, defaultValue = "0")
        Float elementRate();
    }
}
