package com.parch.combine.ui.elements.config;

import com.parch.combine.ui.core.base.element.ElementConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.ui.core.base.trigger.Trigger;
import com.parch.combine.ui.core.base.trigger.TriggerCallFlowConfig;
import com.parch.combine.ui.core.base.trigger.TriggerCallFuncConfig;
import com.parch.combine.ui.core.base.trigger.TriggerCallUrlConfig;
import com.parch.combine.ui.core.base.trigger.TriggerCustomConfig;
import com.parch.combine.ui.core.base.trigger.TriggerLoadConfig;
import com.parch.combine.ui.core.base.trigger.TriggerLoadDataConfig;
import com.parch.combine.ui.core.base.trigger.TriggerSkipConfig;
import com.parch.combine.ui.core.settings.annotations.PageElement;
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

    @Field(key = "content", name = "内容配置", type = FieldTypeEnum.OBJECT, isRequired = true)
    @FieldObject(SubElementConfig.class)
    private SubElementConfig body;

    @Field(key = "closeTriggers", name = "窗口关闭的触发配置", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldRef({TriggerCallFlowConfig.class, TriggerCallFuncConfig.class, TriggerCallUrlConfig.class,
            TriggerCustomConfig.class, TriggerLoadConfig.class, TriggerLoadDataConfig.class, TriggerSkipConfig.class})
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
