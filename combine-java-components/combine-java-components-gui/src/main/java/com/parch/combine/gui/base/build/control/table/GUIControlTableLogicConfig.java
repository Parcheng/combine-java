package com.parch.combine.gui.base.build.control.table;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.build.GUIControlLogicConfig;
import com.parch.combine.gui.core.element.sub.SubElementLogicConfig;

import java.util.Map;

public interface GUIControlTableLogicConfig extends GUIControlLogicConfig {

    @Field(key = "value", name = "表格数据", type = FieldTypeEnum.MAP, isArray = true, isRequired = true)
    Map<String, Object>[] value();

    @Field(key = "minRow", name = "最小显示行", type = FieldTypeEnum.NUMBER)
    Integer minRow();

    @Field(key = "headNames", name = "表格头", type = FieldTypeEnum.TEXT, isArray = true, isRequired = true)
    String[] headNames();

    @Field(key = "rowElements", name = "行GUI元素ID", type = FieldTypeEnum.CONFIG, isArray = true, isRequired = true)
    @FieldObject(SubElementLogicConfig.class)
    SubElementLogicConfig[] rowElements();
}
