package com.parch.combine.gui.core.style.config;

import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.style.ElementObjectConstant;

import java.util.List;

@CommonObject(key = ElementObjectConstant.GUI_ELEMENT_FONT, name = ElementObjectConstant.GUI_ELEMENT_FONT_NAME)
public class ElementFontConfig {

    @Field(key = "names", name = "字体名称集合", type = FieldTypeEnum.SELECT)
    @FieldDesc("以集合的顺序为优先级设置字体，如果操作系统该字体不存在则取下一项，如果没有匹配的则使用系统默认")
    private List<String> names;

    @Field(key = "size", name = "字体大小", type = FieldTypeEnum.TEXT, defaultValue = "14")
    private Integer size;

    @Field(key = "bold", name = "字体加粗", type = FieldTypeEnum.BOOLEAN)
    private Boolean bold;

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Boolean getBold() {
        return bold;
    }

    public void setBold(Boolean bold) {
        this.bold = bold;
    }
}
