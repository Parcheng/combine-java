package com.parch.combine.gui.base.build.control.paging;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.build.GUIControlInitConfig;

public interface GUIControlPagingInitConfig extends GUIControlInitConfig {

    @Field(key = "previousText", name = "上一页显示文本", type = FieldTypeEnum.TEXT, defaultValue = "<")
    String previousText();

    @Field(key = "nextText", name = "下一页页显示文本", type = FieldTypeEnum.TEXT, defaultValue = ">")
    String nextText();

    @Field(key = "firstText", name = "首页显示文本", type = FieldTypeEnum.TEXT, defaultValue = "HOME")
    String firstText();

    @Field(key = "lastText", name = "末页显示文本", type = FieldTypeEnum.TEXT, defaultValue = "END")
    String lastText();

    @Field(key = "template", name = "模板样式配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(GUIPagingElementTemplate.class)
    GUIPagingElementTemplate template();
}
