package com.parch.combine.html.base.element;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.html.base.template.VideoElementTemplateLogicConfig;
import com.parch.combine.ui.core.base.element.ElementConfig;
import com.parch.combine.ui.core.settings.annotations.PageElement;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

import java.util.List;

@PageElement(key = "video", name = "视频元素", templateClass = VideoElementTemplateLogicConfig.class)
public class VideoElementConfig extends ElementConfig<VideoElementTemplateLogicConfig> {

    @Field(key = "src", name = "视频地址", type = FieldTypeEnum.TEXT, isRequired = true)
    private String src;

    @Field(key = "text", name = "不兼容时显示的文本提示信息", type = FieldTypeEnum.TEXT)
    private String text;

    public VideoElementConfig() {
        super(SystemElementPathTool.buildJsPath("video"), SystemElementPathTool.buildCssPath("video"),
                SystemElementPathTool.buildTemplatePath("video"), VideoElementTemplateLogicConfig.class);
    }

    @Override
    protected void initConfig() {}

    @Override
    protected List<String> checkConfig() {
        return null;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
