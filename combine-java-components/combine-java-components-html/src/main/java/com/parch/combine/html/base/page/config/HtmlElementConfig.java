package com.parch.combine.html.base.page.config;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldDesc;
import com.parch.combine.core.component.settings.annotations.FieldRef;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.html.base.template.core.DomConfig;

public interface HtmlElementConfig {

    @Field(key = "key", name = "配置KEY", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldDesc("对应模板中每部分的KEY，系统内置模板包含：header、footer、left、right、content 五部分")
    String key();

    @Field(key = "defaultShowGroupId", name = "默认展示的元素组ID", type = FieldTypeEnum.TEXT)
    String defaultShowGroupId();

    @Field(key = "config", name = "DOM元素配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldDesc("如果不配置DOM元素的 id 属性，则默认使用 key 属性的值")
    @FieldRef(DomConfig.class)
    DomConfig config();
}
