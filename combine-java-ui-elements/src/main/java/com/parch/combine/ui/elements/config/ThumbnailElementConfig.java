package com.parch.combine.ui.elements.config;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.element.ElementConfig;
import com.parch.combine.core.ui.base.element.SubElement;
import com.parch.combine.core.ui.settings.annotations.PageElement;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

import java.util.List;

@PageElement(key = "thumbnail", name = "缩略图元素", templateClass = ThumbnailElementTemplateConfig.class)
public class ThumbnailElementConfig extends ElementConfig<ThumbnailElementTemplateConfig> {

    @Field(key = "path", name = "图片路径", type = FieldTypeEnum.TEXT, isRequired = true)
    private String path;

    @Field(key = "title", name = "内容标题", type = FieldTypeEnum.TEXT)
    private String title;

    @Field(key = "text", name = "内容的每行文本", type = FieldTypeEnum.TEXT, isArray = true)
    private List<String> text;

    @Field(key = "buttons", name = "操作按钮配置集合", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldObject(type = ButtonElementConfig.ButtonItemSettings.class)
    @SubElement
    private List<Object> buttons;

    public ThumbnailElementConfig() {
        super(SystemElementPathTool.buildJsPath("thumbnail"), SystemElementPathTool.buildTemplatePath("thumbnail"), ThumbnailElementTemplateConfig.class);
    }

    @Override
    protected void initConfig() {}

    @Override
    protected List<String> checkConfig() {
        return null;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

    public List<Object> getButtons() {
        return buttons;
    }

    public void setButtons(List<Object> buttons) {
        this.buttons = buttons;
    }
}
