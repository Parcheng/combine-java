package com.parch.combine.html.old.base.element.core;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;

public interface ElementConfig  {

    @Field(key = "id", name = "元素ID（默认随机生成）", type = FieldTypeEnum.TEXT)
    String id();

    @Field(key = "data", name = "初始数据", type = FieldTypeEnum.ANY)
    Object data();

    @Field(key = "refresh", name = "是否支持刷新", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    Boolean refresh();

    @Field(key = "defaultData", name = "默认数据", type = FieldTypeEnum.ANY)
    Object defaultData();

    @Field(key = "dataload", name = "数据加载配置/配置ID", type = FieldTypeEnum.COMPONENT)
    String dataload();

    @Field(key = "defaultLoad", name = "是否默认加载（构建元素时加载数据）", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    Boolean defaultLoad();

    @Field(key = "dataField", name = "数据二次取值的字段名", type = FieldTypeEnum.TEXT)
    String dataField();

    @Field(key = "template", name = "页面元素模板配置/配置ID", type = FieldTypeEnum.COMPONENT)
    String template();
}
