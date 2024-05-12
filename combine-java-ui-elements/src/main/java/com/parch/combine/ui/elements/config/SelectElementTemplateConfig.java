package com.parch.combine.ui.elements.config;

import com.parch.combine.core.ui.base.DomConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.element.ElementTemplateConfig;
import com.parch.combine.core.ui.settings.PageSettingCanstant;

public class SelectElementTemplateConfig extends ElementTemplateConfig {

    @Field(key = "select", name = "下拉框DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig select;

    @Field(key = "selectValue", name = "下拉框值DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig selectValue;

    @Field(key = "selectOptions", name = "下拉框选项集合DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig selectOptions;

    @Field(key = "selectOptionFlag", name = "下拉标识DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig selectOptionFlag;

    @Field(key = "selectOptionItem", name = "下拉选项DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig selectOptionItem;

    public DomConfig getSelect() {
        return select;
    }

    public void setSelect(DomConfig select) {
        this.select = select;
    }

    public DomConfig getSelectValue() {
        return selectValue;
    }

    public void setSelectValue(DomConfig selectValue) {
        this.selectValue = selectValue;
    }

    public DomConfig getSelectOptionItem() {
        return selectOptionItem;
    }

    public void setSelectOptionItem(DomConfig selectOptionItem) {
        this.selectOptionItem = selectOptionItem;
    }

    public DomConfig getSelectOptions() {
        return selectOptions;
    }

    public void setSelectOptions(DomConfig selectOptions) {
        this.selectOptions = selectOptions;
    }

    public DomConfig getSelectOptionFlag() {
        return selectOptionFlag;
    }

    public void setSelectOptionFlag(DomConfig selectOptionFlag) {
        this.selectOptionFlag = selectOptionFlag;
    }
}
