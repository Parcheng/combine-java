package com.parch.combine.ui.elements.templates;

import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.SelectSettings;
import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@CommonObject(order = 1, key = WebSettingCanstant.ELEMENT_ENTITY_KEY, name = "下拉选择框页面元素", desc = "当 TYPE = SELECT 时的参数列表")
public class SelectElementEntity extends ElementEntity<SelectSettings> {

    @Field(key = "select", name = "下拉框DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig select;

    @Field(key = "selectValue", name = "下拉框值DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig selectValue;

    @Field(key = "selectOptions", name = "下拉框选项集合DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig selectOptions;

    @Field(key = "selectOptionFlag", name = "下拉标识DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig selectOptionFlag;

    @Field(key = "selectOptionItem", name = "下拉选项DOM配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig selectOptionItem;

    @Field(key = "settings", name = "元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = SelectSettings.class)
    private SelectSettings settings;

    public SelectElementEntity() {
        super(ElementTypeEnum.SELECT);
    }

    @Override
    public SelectSettings getSettings() {
        return settings;
    }

    @Override
    public void setSettings(SelectSettings settings) {
        this.settings = settings;
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
