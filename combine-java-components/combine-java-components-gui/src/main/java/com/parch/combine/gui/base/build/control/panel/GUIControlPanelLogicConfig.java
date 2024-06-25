package com.parch.combine.gui.base.build.control.panel;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.build.GUIControlLogicConfig;
import com.parch.combine.gui.core.element.sub.SubElementLogicConfig;

public interface GUIControlPanelLogicConfig extends GUIControlLogicConfig {

    @Field(key = "value", name = "数据", type = FieldTypeEnum.ANY)
    Object value();

    @Field(key = "bodyElements", name = "内容配置集合", type = FieldTypeEnum.CONFIG, isArray = true, isRequired = true)
    @FieldObject(SubElementLogicConfig.class)
    SubElementLogicConfig[] bodyElements();
}
