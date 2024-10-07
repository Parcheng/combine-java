package com.parch.combine.html.base.element;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface TreeElementLogicConfig extends ILogicConfig {

    @Field(key = "checkedField", name = "是否被选择", type = FieldTypeEnum.TEXT, defaultValue = "false")
    String checkedField();

    @Field(key = "textField", name = "文本内容", type = FieldTypeEnum.TEXT, isRequired = true)
    String textField();

    @Field(key = "childrenField", name = "子树", type = FieldTypeEnum.TEXT)
    String childrenField();

    @Field(key = "checkFirst", name = "是否默认选择第一项", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    Boolean checkFirst();

    @Field(key = "triggers", name = "树项触发配置", type = FieldTypeEnum.COMPONENT, isArray = true)
    String[] triggers();
}
