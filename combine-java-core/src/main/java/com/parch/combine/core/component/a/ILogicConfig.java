package com.parch.combine.core.component.a;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ComponentFlagEnum;

import java.util.ArrayList;
import java.util.List;

public interface ILogicConfig {

    @Field(key = "id", name = "逻辑配置ID", type = FieldTypeEnum.ID)
    String id();

    @Field(key = "type", name = "组件类型", type = FieldTypeEnum.TEXT, isRequired = true)
    String type();

    @Field(key = "ref", name = "关联初始化配置的ID", type = FieldTypeEnum.TEXT)
    String ref();

    @Field(key = "flag", name = "标识", type = FieldTypeEnum.SELECT, isArray = true)
    List<ComponentFlagEnum> flags();

    @Field(key = "showMsg", name = "自定义错误信息", type = FieldTypeEnum.TEXT)
    String showMsg();

    @Field(key = "printResult", name = "是否组件打印执行结果", type = FieldTypeEnum.BOOLEAN)
    Boolean printResult();
}
