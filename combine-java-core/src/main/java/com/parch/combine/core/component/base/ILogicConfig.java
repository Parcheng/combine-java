package com.parch.combine.core.component.base;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import java.util.List;

public interface ILogicConfig {

    @Field(key = "id", name = "逻辑配置ID", order = 0.1F, type = FieldTypeEnum.ID, parseExpression = false)
    @FieldDesc("组件ID，默认为随机字符串")
    @FieldEg(eg = "component_logic_config_001")
    String id();

    @Field(key = "type", name = "组件类型", order = 0.2F, type = FieldTypeEnum.TEXT, parseExpression = false, isRequired = true)
    String type();

    @Field(key = "ref", name = "关联初始化配置的ID", order = 0.3F, type = FieldTypeEnum.TEXT)
    @FieldDesc("如果同类型组件未配置多项初始化配置，无需填写此参数，默认：使用该组件类的第一项初始化配置")
    @FieldEg(eg = "component_init_config_001", desc = "引用ID为component_init_config_001的初始化配置")
    String ref();

    @Field(key = "flags", name = "标识", order = 0.4F, type = FieldTypeEnum.SELECT, parseExpression = false, isArray = true)
    @FieldSelect(enumClass = ComponentFlagEnum.class)
    String[] flags();

    @Field(key = "showMsg", name = "自定义错误信息", order = 0.5F, type = FieldTypeEnum.TEXT)
    @FieldDesc("当组件执行异常时返回该自定义错误信息，默认：返回系统内置的错误提示信息")
    @FieldEg(eg = "XX组件执行错误")
    String showMsg();

    @Field(key = "printResult", name = "是否组件打印执行结果", order = 0.6F, type = FieldTypeEnum.BOOLEAN)
    @FieldDesc("不设置时，以全局配置为准")
    Boolean printResult();
}
