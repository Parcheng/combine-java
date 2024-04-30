package com.parch.combine.component.web.elements.entity;

import com.parch.combine.component.web.elements.settings.SelectSettings;
import com.parch.combine.component.web.elements.enums.ElementTypeEnum;
import com.parch.combine.component.web.ElementDomConfig;

/**
 * 配置类
 */
public class SelectElementEntity extends ElementEntity<SelectSettings> {

    private ElementDomConfig external;

    private ElementDomConfig select;
    private ElementDomConfig selectValue;
    private ElementDomConfig selectOptions;
    private ElementDomConfig selectOptionFlag;
    private ElementDomConfig selectOptionItem;

    public SelectElementEntity() {
        super(ElementTypeEnum.SELECT);
    }

    public ElementDomConfig getExternal() {
        return external;
    }

    public void setExternal(ElementDomConfig external) {
        this.external = external;
    }

    public ElementDomConfig getSelect() {
        return select;
    }

    public void setSelect(ElementDomConfig select) {
        this.select = select;
    }

    public ElementDomConfig getSelectValue() {
        return selectValue;
    }

    public void setSelectValue(ElementDomConfig selectValue) {
        this.selectValue = selectValue;
    }

    public ElementDomConfig getSelectOptionItem() {
        return selectOptionItem;
    }

    public void setSelectOptionItem(ElementDomConfig selectOptionItem) {
        this.selectOptionItem = selectOptionItem;
    }

    public ElementDomConfig getSelectOptions() {
        return selectOptions;
    }

    public void setSelectOptions(ElementDomConfig selectOptions) {
        this.selectOptions = selectOptions;
    }

    public ElementDomConfig getSelectOptionFlag() {
        return selectOptionFlag;
    }

    public void setSelectOptionFlag(ElementDomConfig selectOptionFlag) {
        this.selectOptionFlag = selectOptionFlag;
    }
}
