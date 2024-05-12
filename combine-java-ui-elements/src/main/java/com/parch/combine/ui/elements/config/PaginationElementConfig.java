package com.parch.combine.ui.elements.config;

import com.parch.combine.core.ui.base.element.ElementConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.trigger.Trigger;
import com.parch.combine.core.ui.settings.PageSettingCanstant;
import com.parch.combine.core.ui.settings.annotations.PageElement;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

@PageElement(key = "pagination", name = "分页元素", templateClass = AudioElementTemplateConfig.class)
public class PaginationElementConfig extends ElementConfig<PaginationElementTemplateConfig> {

    @Field(key = "currPage", name = "当前页", type = FieldTypeEnum.TEXT, isRequired = true)
    private String currPage;

    @Field(key = "maxPage", name = "最大页", type = FieldTypeEnum.TEXT, isRequired = true)
    private String maxPage;

    @Field(key = "maxLength", name = "可选页最大长度", type = FieldTypeEnum.TEXT, defaultValue = "10")
    private Integer maxLength = 10;

    @Field(key = "triggers", name = "选页触发配置", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldRef(key = PageSettingCanstant.TRIGGER_KEY)
    @Trigger
    private Object triggers;

    public PaginationElementConfig() {
        super(SystemElementPathTool.buildJsPath("pagination"), SystemElementPathTool.buildTemplatePath("pagination"), PaginationElementTemplateConfig.class);
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
