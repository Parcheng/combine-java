package com.parch.combine.gui.base.build.control.list;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.annotations.FieldSelect;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.build.GUIControlLogicConfig;
import com.parch.combine.gui.core.element.sub.SubElementLogicConfig;

public interface GUIControlListLogicConfig extends GUIControlLogicConfig {

    @Field(key = "value", name = "集合数据集合", type = FieldTypeEnum.ANY, isArray = true)
    Object[] value();

    @Field(key = "emptyTipText", name = "空列表提示文本(多行)", type = FieldTypeEnum.TEXT, isArray = true)
    String[] emptyTipText();

    @Field(key = "orientation", name = "排列方式", type = FieldTypeEnum.SELECT, defaultValue = "VERTICAL")
    @FieldSelect(enumClass = ListOrientationEnum.class)
    String orientation();

    @Field(key = "bodyElements", name = "集合内容的GUI元素ID", type = FieldTypeEnum.CONFIG, isArray = true, isRequired = true)
    @FieldObject(SubElementLogicConfig.class)
    SubElementLogicConfig[] bodyElements();
}
