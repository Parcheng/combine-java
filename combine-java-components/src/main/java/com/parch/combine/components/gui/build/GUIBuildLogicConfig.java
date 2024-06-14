package com.parch.combine.components.gui.build;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface GUIBuildLogicConfig extends ILogicConfig {

    @Field(key = "title", name = "标题", type = FieldTypeEnum.TEXT)
    String title();

    @Field(key = "elements", name = "控件元素集合", type = FieldTypeEnum.TEXT, isArray = true)
    String[] elements();

    @Field(key = "visible", name = "是否可见", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    Boolean visible();

    @Field(key = "close", name = "显示关闭按钮", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    Boolean close();
}
