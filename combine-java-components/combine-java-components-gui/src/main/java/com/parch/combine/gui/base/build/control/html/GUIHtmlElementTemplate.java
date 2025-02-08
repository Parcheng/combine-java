package com.parch.combine.gui.base.build.control.html;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.annotations.FieldRef;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.element.BaseGUIElementTemplate;
import com.parch.combine.gui.core.style.ElementConfig;

public class GUIHtmlElementTemplate extends BaseGUIElementTemplate {

    @Field(key = "page", name = "页面模块样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig page;

    public ElementConfig getPage() {
        return page;
    }

    public void setPage(ElementConfig page) {
        this.page = page;
    }
}
