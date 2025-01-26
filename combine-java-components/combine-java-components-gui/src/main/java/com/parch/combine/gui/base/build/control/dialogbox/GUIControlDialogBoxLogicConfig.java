package com.parch.combine.gui.base.build.control.dialogbox;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.build.GUIControlLogicConfig;
import com.parch.combine.gui.core.element.sub.SubElementLogicConfig;

public interface GUIControlDialogBoxLogicConfig extends GUIControlLogicConfig {

    @Field(key = "title", name = "标题", type = FieldTypeEnum.TEXT)
    String title();

    @Field(key = "width", name = "宽度", type = FieldTypeEnum.NUMBER, isRequired = true)
    Integer width();

    @Field(key = "height", name = "高度", type = FieldTypeEnum.NUMBER, isRequired = true)
    Integer height();

    @Field(key = "visible", name = "是否可见", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    Boolean visible();

    @Field(key = "bodyElements", name = "内容配置集合", type = FieldTypeEnum.CONFIG, isArray = true, isRequired = true)
    @FieldObject(SubElementLogicConfig.class)
    SubElementLogicConfig[] bodyElements();
}
