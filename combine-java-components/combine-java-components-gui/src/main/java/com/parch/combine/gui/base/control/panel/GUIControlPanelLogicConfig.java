package com.parch.combine.gui.base.control.panel;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.control.GUIControlLogicConfig;

public interface GUIControlPanelLogicConfig extends GUIControlLogicConfig {

    @Field(key = "data", name = "数据", type = FieldTypeEnum.ANY)
    Object data();

    @Field(key = "bodyElements", name = "内容配置集合", type = FieldTypeEnum.CONFIG, isArray = true, isRequired = true)
    @FieldObject(ElementConfig.class)
    ElementConfig[] bodyElements();

    interface ElementConfig {

        @Field(key = "key", name = "数据值KEY", type = FieldTypeEnum.TEXT)
        @FieldDesc("获取组件值时对应的KEU，默认与 dataField 一致")
        String key();

        @Field(key = "dataField", name = "数据取值字段", type = FieldTypeEnum.TEXT)
        @FieldDesc("从 data 中取 dataField 的值，用于构建 GUI 元素")
        String dataField();

        @Field(key = "elementId", name = "内容的GUI元素ID", type = FieldTypeEnum.TEXT, isRequired = true)
        String elementId();
    }
}
