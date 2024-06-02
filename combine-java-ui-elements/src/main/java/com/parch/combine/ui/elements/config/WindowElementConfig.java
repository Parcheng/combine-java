package com.parch.combine.ui.elements.config;

import com.parch.combine.core.ui.base.element.ElementConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.trigger.Trigger;
import com.parch.combine.core.ui.settings.PageSettingCanstant;
import com.parch.combine.core.ui.settings.annotations.PageElement;
import com.parch.combine.ui.elements.common.SubElementConfig;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

import java.util.List;

@PageElement(key = "window", name = "弹窗元素", templateClass = WindowElementTemplateConfig.class)
public class WindowElementConfig extends ElementConfig<WindowElementTemplateConfig> {

    @Field(key = "title", name = "标题", type = FieldTypeEnum.TEXT)
    private String title;

    @Field(key = "show", name = "默认显示窗口", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    private Boolean show;

    @Field(key = "size", name = "尺寸（宽度）px", type = FieldTypeEnum.NUMBER, defaultValue = "200")
    private Integer size;

    @Field(key = "content", name = "内容配置", type = FieldTypeEnum.ANY, isRequired = true)
    @FieldObject(type = SubElementConfig.class)
    private SubElementConfig body;

    @Field(key = "closeTriggers", name = "窗口关闭的触发配置", type = FieldTypeEnum.ANY, isArray = true)
    @FieldRef(key = PageSettingCanstant.TRIGGER_KEY)
    @Trigger
    private Object closeTriggers;

    public WindowElementConfig() {
        super(SystemElementPathTool.buildJsPath("window"), SystemElementPathTool.buildCssPath("window"),
                SystemElementPathTool.buildTemplatePath("window"), WindowElementTemplateConfig.class);
    }

    @Override
    protected void initConfig() {
        if (show == null) {
            show = false;
        }
        if (size == null) {
            size = 200;
        }
    }

    @Override
    protected List<String> checkConfig() {
        return null;
    }

    public Object getCloseTriggers() {
        return closeTriggers;
    }

    public void setCloseTriggers(Object closeTriggers) {
        this.closeTriggers = closeTriggers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SubElementConfig getBody() {
        return body;
    }

    public void setBody(SubElementConfig body) {
        this.body = body;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
