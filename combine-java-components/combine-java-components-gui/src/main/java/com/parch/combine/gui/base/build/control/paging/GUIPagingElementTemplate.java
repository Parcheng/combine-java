package com.parch.combine.gui.base.build.control.paging;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.element.BaseGUIElementTemplate;
import com.parch.combine.gui.core.style.ElementConfig;
import com.parch.combine.gui.core.style.ElementObjectConstant;

public class GUIPagingElementTemplate extends BaseGUIElementTemplate {

    @Field(key = "page", name = "页码样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig page;

    @Field(key = "checked", name = "选中时样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig checked;

    @Field(key = "disable", name = "禁选时样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ElementConfig.class)
    @FieldRef(ElementConfig.class)
    private ElementConfig disable;

    public ElementConfig getPage() {
        return page;
    }

    public void setPage(ElementConfig page) {
        this.page = page;
    }

    public ElementConfig getChecked() {
        return checked;
    }

    public void setChecked(ElementConfig checked) {
        this.checked = checked;
    }

    public ElementConfig getDisable() {
        return disable;
    }

    public void setDisable(ElementConfig disable) {
        this.disable = disable;
    }
}
