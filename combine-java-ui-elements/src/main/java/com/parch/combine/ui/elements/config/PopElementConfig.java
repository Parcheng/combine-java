package com.parch.combine.ui.elements.config;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.ui.core.base.element.ElementConfig;
import com.parch.combine.ui.core.settings.annotations.PageElement;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

import java.util.List;

@PageElement(key = "pop", name = "泡泡窗元素", templateClass = PopElementTemplateConfig.class)
public class PopElementConfig extends ElementConfig<PopElementTemplateConfig> {

    @Field(key = "popType", name = "样式类型（可选值取决于模板配置）", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldDesc("系统内置模板支持的类型：normal | success | info | warn | error")
    private String popType;

    @Field(key = "size", name = "尺寸（宽度）px", type = FieldTypeEnum.NUMBER)
    private Integer size;

    @Field(key = "text", name = "内容文本", type = FieldTypeEnum.TEXT, isRequired = true)
    private String text;

    @Field(key = "hasClose", name = "是否支持关闭", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    private Boolean hasClose = false;

    public PopElementConfig() {
        super(SystemElementPathTool.buildJsPath("pop"), SystemElementPathTool.buildCssPath("pop"),
                SystemElementPathTool.buildTemplatePath("pop"), PopElementTemplateConfig.class);
    }

    @Override
    protected void initConfig() {}

    @Override
    protected List<String> checkConfig() {
        return null;
    }

    public String getPopType() {
        return popType;
    }

    public void setPopType(String popType) {
        this.popType = popType;
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

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
