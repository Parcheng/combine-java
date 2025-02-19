package com.parch.combine.html.base.element;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.base.element.core.ElementConfig;

public interface FromElementLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "页面元素配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(Config.class)
    Config config();

    interface Config extends ElementConfig {

        @Field(key = "layout", name = "布局 VERTICAL | INLINE | HORIZONTAL", type = FieldTypeEnum.TEXT, defaultValue = "HORIZONTAL")
        String layout();

        @Field(key = "column", name = "列数（1-100）", type = FieldTypeEnum.NUMBER, defaultValue = "1")
        Integer column();

        @Field(key = "items", name = "表单项配置", type = FieldTypeEnum.CONFIG, isRequired = true, isArray = true)
        @FieldObject(ItemConfig.class)
        ItemConfig[] items();
    }

    interface ItemConfig {

        @Field(key = "id", name = "配置项ID（DOM元素ID）", type = FieldTypeEnum.TEXT)
        String id();

        @Field(key = "fieldKey", name = "表单字段KEY", type = FieldTypeEnum.TEXT)
        String fieldKey();

        @Field(key = "fieldName", name = "表单字段名称", type = FieldTypeEnum.TEXT)
        String fieldName();

        @Field(key = "text", name = "text 和 element 配置任意一项即可", type = FieldTypeEnum.TEXT)
        String text();

        @Field(key = "hide", name = "是否隐藏", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
        Boolean hide();

        @Field(key = "requiredFlag", name = "是否显示必填标识", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
        Boolean requiredFlag();

        @Field(key = "desc", name = "描述信息（或字段名）", type = FieldTypeEnum.TEXT)
        String desc();

        @Field(key = "showDesc", name = "是否默认显示描述", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
        Boolean showDesc();

        @Field(key = "error", name = "错误信息（或字段名）", type = FieldTypeEnum.TEXT)
        String error();

        @Field(key = "showError", name = "是否默认显示错误信息", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
        Boolean showError();

        @Field(key = "element", name = "表单元素ID", type = FieldTypeEnum.COMPONENT)
        String element();
    }
}
