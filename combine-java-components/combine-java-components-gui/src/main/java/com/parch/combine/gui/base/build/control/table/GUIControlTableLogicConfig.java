package com.parch.combine.gui.base.build.control.table;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.build.GUIControlLogicConfig;
import com.parch.combine.gui.core.element.sub.SubElementLogicConfig;

import java.util.Map;

public interface GUIControlTableLogicConfig extends GUIControlLogicConfig {

    @Field(key = "value", name = "表格数据", type = FieldTypeEnum.MAP, isArray = true, isRequired = true)
    Map<String, Object>[] value();

    @Field(key = "minRow", name = "最小显示行", type = FieldTypeEnum.NUMBER)
    Integer minRow();

    @Field(key = "rowHeight", name = "行高度", type = FieldTypeEnum.NUMBER)
    Integer rowHeight();

    @Field(key = "rowMargin", name = "行内容边距", type = FieldTypeEnum.NUMBER)
    Integer rowMargin();

    @Field(key = "headNames", name = "表格头", type = FieldTypeEnum.TEXT, isArray = true, isRequired = true)
    String[] headNames();

    @Field(key = "rowElements", name = "行GUI元素ID", type = FieldTypeEnum.CONFIG, isArray = true, isRequired = true)
    @FieldObject(SubElementLogicConfig.class)
    SubElementLogicConfig[] rowElements();
}
