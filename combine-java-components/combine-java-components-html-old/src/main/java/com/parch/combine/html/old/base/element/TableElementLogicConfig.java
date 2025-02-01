package com.parch.combine.html.old.base.element;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.annotations.FieldDesc;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.old.base.element.core.ElementConfig;

public interface TableElementLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "页面元素配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(Config.class)
    Config config();

    interface Config extends ElementConfig {

        @Field(key = "fieldNames", name = "每列对应的字段名配置集合", type = FieldTypeEnum.TEXT, isArray = true)
        String[] fieldNames();

        @Field(key = "dataFieldNames", name = "数据字段集合", type = FieldTypeEnum.TEXT, isArray = true)
        @FieldDesc("这些字段会被保存到表格数据中，但不显示在表格上，可以通过获取表格数据得到这些字段的值")
        String[] dataFieldNames();

        @Field(key = "headNames", name = "表头文本集合", type = FieldTypeEnum.TEXT, isArray = true)
        String[] headNames();

        @Field(key = "minLength", name = "最小行数（数据不够空行补全）", type = FieldTypeEnum.NUMBER, defaultValue = "10")
        Integer minLength();

        @Field(key = "hasChecked", name = "是否允许选择行", type = FieldTypeEnum.BOOLEAN)
        Boolean hasChecked();

        @Field(key = "hasIndex", name = "是否显示行号", type = FieldTypeEnum.BOOLEAN)
        Boolean hasIndex();

        @Field(key = "rowOpts", name = "操作栏的操作元素配置", type = FieldTypeEnum.COMPONENT, isArray = true)
        String[] rowOpts();
    }
}
