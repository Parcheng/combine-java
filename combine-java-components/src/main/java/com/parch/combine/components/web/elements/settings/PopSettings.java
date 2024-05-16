package com.parch.combine.components.web.elements.settings;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 页面元素设置
 */
public class PopSettings extends BaseSettings{

    @Field(key = "type", name = "样式类型（可选值取决于模板配置）", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldDesc("系统内置模板支持的类型：normal | success | info | warn | error")
    private String type;

    @Field(key = "text", name = "内容文本", type = FieldTypeEnum.TEXT, isRequired = true)
    private String text;

    @Field(key = "hasClose", name = "是否支持关闭", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    private Boolean hasClose = false;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getHasClose() {
        return hasClose;
    }

    public void setHasClose(Boolean hasClose) {
        this.hasClose = hasClose;
    }
}
