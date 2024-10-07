package com.parch.combine.html.base.element;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.html.base.template.PaginationElementTemplateLogicConfig;
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

@PageElement(key = "pagination", name = "分页元素", templateClass = PaginationElementTemplateLogicConfig.class)
public class PaginationElementConfig extends ElementConfig<PaginationElementTemplateLogicConfig> {

    @Field(key = "currPage", name = "当前页", type = FieldTypeEnum.TEXT, isRequired = true)
    private String currPage;

    @Field(key = "maxPage", name = "最大页", type = FieldTypeEnum.TEXT, isRequired = true)
    private String maxPage;

    @Field(key = "maxLength", name = "可选页最大长度", type = FieldTypeEnum.TEXT, defaultValue = "10")
    private Integer maxLength = 10;

    @Field(key = "triggers", name = "选页触发配置", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldRef({TriggerCallFlowConfig.class, TriggerCallFuncConfig.class, TriggerCallUrlConfig.class, 
            TriggerCustomConfig.class, TriggerLoadConfig.class, TriggerLoadDataConfig.class, TriggerSkipConfig.class})
    @Trigger
    private Object triggers;

    public PaginationElementConfig() {
        super(SystemElementPathTool.buildJsPath("pagination"), SystemElementPathTool.buildCssPath("pagination"),
                SystemElementPathTool.buildTemplatePath("pagination"), PaginationElementTemplateLogicConfig.class);
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

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public Object getTriggers() {
        return triggers;
    }

    public void setTriggers(Object triggers) {
        this.triggers = triggers;
    }
}
