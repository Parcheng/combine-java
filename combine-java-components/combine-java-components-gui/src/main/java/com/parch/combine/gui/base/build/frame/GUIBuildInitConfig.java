package com.parch.combine.gui.base.build.frame;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.IInitConfig;
import com.parch.combine.gui.base.build.control.button.GUIButtonElementTemplate;

public interface GUIBuildInitConfig extends IInitConfig {

    @Field(key = "template", name = "模板样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(GUIButtonElementTemplate.class)
    GUIFrameTemplate template();
}
