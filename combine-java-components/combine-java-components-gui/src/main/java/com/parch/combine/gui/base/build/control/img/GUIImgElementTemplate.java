package com.parch.combine.gui.base.build.control.img;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.element.BaseGUIElementTemplate;
import com.parch.combine.gui.core.style.ElementConfig;
import com.parch.combine.gui.core.style.ElementObjectConstant;

public class GUIImgElementTemplate extends BaseGUIElementTemplate {

    @Field(key = "img", name = "图片元素样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig img;

    public ElementConfig getImg() {
        return img;
    }

    public void setImg(ElementConfig img) {
        this.img = img;
    }
}
