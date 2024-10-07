package com.parch.combine.html.base.element;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.html.base.template.PageTurningElementTemplateLogicConfig;
import com.parch.combine.ui.core.base.element.ElementConfig;
import com.parch.combine.ui.core.base.trigger.Trigger;
import com.parch.combine.ui.core.base.trigger.TriggerCallFlowConfig;
import com.parch.combine.ui.core.base.trigger.TriggerCallFuncConfig;
import com.parch.combine.ui.core.base.trigger.TriggerCallUrlConfig;
import com.parch.combine.ui.core.base.trigger.TriggerCustomConfig;
import com.parch.combine.ui.core.base.trigger.TriggerLoadConfig;
import com.parch.combine.ui.core.base.trigger.TriggerLoadDataConfig;
import com.parch.combine.ui.core.base.trigger.TriggerSkipConfig;
import com.parch.combine.ui.core.settings.annotations.PageElement;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

import java.util.List;

@PageElement(key = "page_turning", name = "翻页元素", templateClass = PageTurningElementTemplateLogicConfig.class)
public class PageTurningElementConfig extends ElementConfig<PageTurningElementTemplateLogicConfig> {

    @Field(key = "currPage", name = "当前页", type = FieldTypeEnum.TEXT, isRequired = true)
    private String currPage;

    @Field(key = "maxPage", name = "最大页", type = FieldTypeEnum.TEXT, isRequired = true)
    private String maxPage;

    @Field(key = "triggers", name = "翻页触发配置", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldRef({TriggerCallFlowConfig.class, TriggerCallFuncConfig.class, TriggerCallUrlConfig.class, 
            TriggerCustomConfig.class, TriggerLoadConfig.class, TriggerLoadDataConfig.class, TriggerSkipConfig.class})
    @Trigger
    private Object triggers;

    public PageTurningElementConfig() {
        super(SystemElementPathTool.buildJsPath("page_turning"), SystemElementPathTool.buildCssPath("page_turning"),
                SystemElementPathTool.buildTemplatePath("page_turning"), PageTurningElementTemplateLogicConfig.class);
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
