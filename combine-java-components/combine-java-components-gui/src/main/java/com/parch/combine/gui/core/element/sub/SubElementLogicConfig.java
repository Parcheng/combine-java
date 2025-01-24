package com.parch.combine.gui.core.element.sub;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldDesc;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.event.EventConfig;

public interface SubElementLogicConfig {

    @Field(key = "key", name = "数据值KEY", type = FieldTypeEnum.TEXT)
    @FieldDesc("获取组件值时对应的KEU，默认与 dataField 一致")
    String key();

    @Field(key = "dataField", name = "数据取值字段", type = FieldTypeEnum.TEXT)
    @FieldDesc("从 data 中取 dataField 的值，用于构建 GUI 元素")
    String dataField();

    @Field(key = "defaultValue", name = "默认值", type = FieldTypeEnum.ANY)
    Object defaultValue();

    @Field(key = "hasNullAssignment", name = "是否允许空赋值", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    @FieldDesc("当的数据为空时，如果该值为 true 表示覆盖原有GUI控件值，否则忽略该空数据不覆盖原有GUI控件值")
    Boolean hasNullAssignment();

    @Field(key = "elementId", name = "内容的GUI元素ID", type = FieldTypeEnum.TEXT, isRequired = true)
    String elementId();

    @Field(key = "events", name = "事件配置集合", type = FieldTypeEnum.CONFIG, isArray = true)
    @FieldObject(EventConfig.class)
    EventConfig[] events();
}
