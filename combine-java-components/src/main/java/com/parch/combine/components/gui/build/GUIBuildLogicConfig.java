package com.parch.combine.components.gui.build;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface GUIBuildLogicConfig extends ILogicConfig {

    @Field(key = "title", name = "标题", type = FieldTypeEnum.TEXT)
    String title();

    @Field(key = "topElements", name = "顶部控件元素集合", type = FieldTypeEnum.TEXT, isArray = true)
    String[] topElements();

    @Field(key = "bottomElement", name = "底部控件元素集合", type = FieldTypeEnum.TEXT, isArray = true)
    String[] bottomElement();

    @Field(key = "leftElement", name = "左侧控件元素集合", type = FieldTypeEnum.TEXT, isArray = true)
    String[] leftElement();

    @Field(key = "rightElement", name = "右侧控件元素集合", type = FieldTypeEnum.TEXT, isArray = true)
    String[] rightElement();

    @Field(key = "centerElements", name = "中部控件元素集合", type = FieldTypeEnum.TEXT, isArray = true)
    String[] centerElements();

    @Field(key = "visible", name = "是否可见", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    Boolean visible();

    @Field(key = "close", name = "显示关闭按钮", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    Boolean close();
}
