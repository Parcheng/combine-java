package com.parch.combine.gui.core.style.config;

import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.style.ElementObjectConstant;

@CommonObject(key = ElementObjectConstant.GUI_ELEMENT_FONT, name = ElementObjectConstant.GUI_ELEMENT_FONT_NAME)
public class ElementFontConfig {

    @Field(key = "name", name = "字体名称", type = FieldTypeEnum.SELECT, defaultValue = "Arial")
    private String name;

    @Field(key = "size", name = "字体大小", type = FieldTypeEnum.TEXT, defaultValue = "14")
    private Integer size;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
