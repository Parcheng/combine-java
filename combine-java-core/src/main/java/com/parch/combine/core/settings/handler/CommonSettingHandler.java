package com.parch.combine.core.settings.handler;

import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

/**
 * 公共信息设置处理器
 */
public class CommonSettingHandler {

    /**
     * 设置公共属性
     *
     * @param builder 构建对象
     */
    public static void setProperty(ComponentSettingBuilder builder, boolean hasInit) {

        if (hasInit) {
            builder.addProperty(PropertyTypeEnum.INIT, "id", FieldTypeEnum.TEXT, "组件初始化配置ID", false, false);
            builder.addPropertyDesc(PropertyTypeEnum.INIT, "id", "同一类型的组件可以存在多个初始化配置，可以再逻辑配置中通过ref字段来指定使用哪一个初始化配置");
            builder.addProperty(PropertyTypeEnum.INIT, "type", FieldTypeEnum.TEXT, "组件类型", true, false);


            builder.addPropertyEg(PropertyTypeEnum.INIT, "id", "component_init_config_001", null);
            builder.addPropertyEg(PropertyTypeEnum.INIT, "type", builder.get().getKey(), null);
        }


        builder.addProperty(PropertyTypeEnum.LOGIC, "id", FieldTypeEnum.SELECT, "逻辑配置ID",false, false);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "id", "组件ID，默认为随机字符串");
        builder.addProperty(PropertyTypeEnum.LOGIC, "type", FieldTypeEnum.TEXT, "组件类型", true, false);
        builder.addProperty(PropertyTypeEnum.LOGIC, "flag", FieldTypeEnum.TEXT, "标识", false, false);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "flag", "可选值：STATIC（表示静态组件不会自动执行，必须被逻辑组件触发才能执行）");
        builder.addProperty(PropertyTypeEnum.LOGIC, "showMsg", FieldTypeEnum.TEXT, "自定义错误信息", false, false);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "showMsg", "当组件执行异常时返回该自定义错误信息，默认：返回系统内置的错误提示信息");
        builder.addProperty(PropertyTypeEnum.LOGIC, "ref", FieldTypeEnum.TEXT, "关联初始化配置的ID", false, false);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "ref", "如果同类型组件未配置多项初始化配置，无需填写此参数，默认：使用该组件类的第一项初始化配置");


        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "id", "component_logic_config_001", null);
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "type", builder.get().getKey(), null);
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "showMsg", "XX组件执行错误",  null);
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "ref", "component_init_config_001", "引用ID为component_init_config_001的初始化配置");
    }
}
