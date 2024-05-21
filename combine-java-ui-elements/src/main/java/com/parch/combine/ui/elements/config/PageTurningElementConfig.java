package com.parch.combine.ui.elements.config;

import com.parch.combine.core.ui.base.element.ElementConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.trigger.Trigger;
import com.parch.combine.core.ui.settings.PageSettingCanstant;
import com.parch.combine.core.ui.settings.annotations.PageElement;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

import java.util.List;

@PageElement(key = "page_turning", name = "翻页元素", templateClass = AudioElementTemplateConfig.class)
public class PageTurningElementConfig extends ElementConfig<PageTurningElementTemplateConfig> {

    @Field(key = "currPage", name = "当前页", type = FieldTypeEnum.TEXT, isRequired = true)
    private String currPage;

    @Field(key = "maxPage", name = "最大页", type = FieldTypeEnum.TEXT, isRequired = true)
    private String maxPage;

    @Field(key = "triggers", name = "翻页触发配置", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldRef(key = PageSettingCanstant.TRIGGER_KEY)
    @Trigger
    private Object triggers;

    public PageTurningElementConfig() {
        super(SystemElementPathTool.buildJsPath("page_turning"), SystemElementPathTool.buildCssPath("page_turning"),
                SystemElementPathTool.buildTemplatePath("page_turning"), PageTurningElementTemplateConfig.class);
    }

    @Override
    protected void initConfig() {}

    @Override
    protected List<String> checkConfig() {
        return null;
    }

    public String getCurrPage() {
        return currPage;
    }

    public void setCurrPage(String currPage) {
        this.currPage = currPage;
    }

    public String getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(String maxPage) {
        this.maxPage = maxPage;
    }

    public Object getTriggers() {
        return triggers;
    }

    public void setTriggers(Object triggers) {
        this.triggers = triggers;
    }
}
