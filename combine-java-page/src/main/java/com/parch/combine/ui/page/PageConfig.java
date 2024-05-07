package com.parch.combine.ui.page;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.List;

public class PageConfig {

    @Field(key = "id", name = "元素ID", type = FieldTypeEnum.TEXT, defaultValue = "随机字符粗")
    private String id;

    private List<String> elementIds;

    @Field(key = "header", name = "页面头部元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = HtmlElement.class)
    private HtmlElement header;

    @Field(key = "footer", name = "页面底部元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = HtmlElement.class)
    private HtmlElement footer;

    @Field(key = "left", name = "页面左侧元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = HtmlElement.class)
    private HtmlElement left;

    @Field(key = "right", name = "页面右侧元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = HtmlElement.class)
    private HtmlElement right;

    @Field(key = "content", name = "页面内容（中间）元素配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(type = HtmlElement.class)
    private HtmlElement content;
}
