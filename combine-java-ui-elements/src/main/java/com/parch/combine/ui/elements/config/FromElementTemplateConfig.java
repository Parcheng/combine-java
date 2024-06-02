package com.parch.combine.ui.elements.config;

import com.parch.combine.core.ui.base.DomConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.element.ElementTemplateConfig;
import com.parch.combine.core.ui.settings.PageSettingCanstant;

public class FromElementTemplateConfig extends ElementTemplateConfig {

    @Field(key = "from", name = "表单DOM配置", type = FieldTypeEnum.ANY)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig from;

    @Field(key = "item", name = "表单项DOM配置", type = FieldTypeEnum.ANY)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig item;

    @Field(key = "left", name = "左侧标签DOM配置", type = FieldTypeEnum.ANY)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig left;

    @Field(key = "requestFlag", name = "必填标识DOM配置", type = FieldTypeEnum.ANY)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig requestFlag;

    @Field(key = "right", name = "右侧DOM配置", type = FieldTypeEnum.ANY)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig right;

    @Field(key = "rightContent", name = "右侧内容DOM配置", type = FieldTypeEnum.ANY)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig rightContent;

    @Field(key = "rightDesc", name = "右侧内容描述DOM配置", type = FieldTypeEnum.ANY)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig rightDesc;

    @Field(key = "rightError", name = "右侧内容错误信息DOM配置", type = FieldTypeEnum.ANY)
    @FieldRef(key = PageSettingCanstant.DOM_KEY)
    private DomConfig rightError;

    public DomConfig getItem() {
        return item;
    }

    public void setItem(DomConfig item) {
        this.item = item;
    }

    public DomConfig getLeft() {
        return left;
    }

    public void setLeft(DomConfig left) {
        this.left = left;
    }

    public DomConfig getRequestFlag() {
        return requestFlag;
    }

    public void setRequestFlag(DomConfig requestFlag) {
        this.requestFlag = requestFlag;
    }

    public DomConfig getRight() {
        return right;
    }

    public void setRight(DomConfig right) {
        this.right = right;
    }

    public DomConfig getRightContent() {
        return rightContent;
    }

    public void setRightContent(DomConfig rightContent) {
        this.rightContent = rightContent;
    }

    public DomConfig getRightDesc() {
        return rightDesc;
    }

    public void setRightDesc(DomConfig rightDesc) {
        this.rightDesc = rightDesc;
    }

    public DomConfig getRightError() {
        return rightError;
    }

    public void setRightError(DomConfig rightError) {
        this.rightError = rightError;
    }

    public DomConfig getFrom() {
        return from;
    }

    public void setFrom(DomConfig from) {
        this.from = from;
    }
}
