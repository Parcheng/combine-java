package com.parch.combine.html.base.element;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.base.element.core.ElementConfig;

public interface TextElementLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "页面元素配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(Config.class)
    Config config();

    interface Config extends ElementConfig {

        @Field(key = "retract", name = "缩进数", type = FieldTypeEnum.NUMBER)
        Integer retract();

        @Field(key = "lines", name = "行数据配置", type = FieldTypeEnum.CONFIG, isRequired = true, isArray = true)
        @FieldObject(TextLineSettings.class)
        TextLineSettings[] lines();

        @Field(key = "children", name = "子文本数据配置", type = FieldTypeEnum.TEXT, isArray = true)
        String[] children();

        @Field(key = "defaultText", name = "所有文本为空时默认显示文本", type = FieldTypeEnum.TEXT)
        String defaultText();
    }

    interface TextLineSettings{

        @Field(key = "title", name = "行标题配置", type = FieldTypeEnum.TEXT)
        String title();

        @Field(key = "data", name = "行数据二次取值", type = FieldTypeEnum.TEXT)
        String data();

        @Field(key = "text", name = "行文本", type = FieldTypeEnum.TEXT)
        String text();

        @Field(key = "separator", name = "行数据为多条文本时的分隔符", type = FieldTypeEnum.TEXT, defaultValue = "<br/>")
        String separator();

        @Field(key = "children", name = "子文本数据配置，配置项同该级相同", type = FieldTypeEnum.CONFIG)
        @FieldObject(SubTextConfig.class)
        SubTextConfig children();
    }

    interface SubTextConfig {

        @Field(key = "retract", name = "缩进数", type = FieldTypeEnum.NUMBER)
        Integer retract();

        @Field(key = "lines", name = "行数据配置", type = FieldTypeEnum.CONFIG, isRequired = true, isArray = true)
        @FieldObject(TextLineSettings.class)
        TextLineSettings[] lines();

        @Field(key = "children", name = "子文本数据配置", type = FieldTypeEnum.TEXT, isArray = true)
        String[] children();

        @Field(key = "defaultText", name = "所有文本为空时默认显示文本", type = FieldTypeEnum.TEXT)
        String defaultText();
    }
}
