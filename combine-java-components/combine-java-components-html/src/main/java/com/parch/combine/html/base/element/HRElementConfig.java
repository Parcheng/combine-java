package com.parch.combine.html.base.element;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.html.base.template.HRElementTemplateLogicConfig;
import com.parch.combine.ui.core.base.element.ElementConfig;
import com.parch.combine.ui.core.settings.annotations.PageElement;
import com.parch.combine.ui.elements.tools.SystemElementPathTool;

import java.util.List;

@PageElement(key = "hr", name = "分割线元素", templateClass = HRElementTemplateLogicConfig.class)
public class HRElementConfig extends ElementConfig<HRElementTemplateLogicConfig> {

    @Field(key = "count", name = "分隔线数量", type = FieldTypeEnum.TEXT)
    private Integer count;

    public HRElementConfig() {
        super(SystemElementPathTool.buildJsPath("hr"), SystemElementPathTool.buildCssPath("hr"),
                SystemElementPathTool.buildTemplatePath("hr"), HRElementTemplateLogicConfig.class);
    }

    @Override
    protected void initConfig() {}

    @Override
    protected List<String> checkConfig() {
        return null;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
