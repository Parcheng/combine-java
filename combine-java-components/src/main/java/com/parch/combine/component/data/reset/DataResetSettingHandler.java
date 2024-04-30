package com.parch.combine.component.data.reset;

import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;
import com.parch.combine.core.tools.variable.DataTypeEnum;

import java.util.Arrays;

/**
 * 设置处理器
 */
public class DataResetSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("reset", "数据重置组件", false, DataResetComponent.class);
        // builder.addDesc("数据重置");


        builder.addProperty(PropertyTypeEnum.LOGIC, "replace", FieldTypeEnum.BOOLEAN, "是否替换源数据（默认不替换）",  false, false, "false");
        builder.addProperty(PropertyTypeEnum.LOGIC, "items", FieldTypeEnum.GROUP, "配置项集合，将 “新值” 赋值给 “要重新赋值的字段”",  false, true);
        builder.addPropertyGroup(PropertyTypeEnum.LOGIC, "items", "要重新赋值的字段名", FieldTypeEnum.TEXT,  true, null);
        builder.addPropertyGroup(PropertyTypeEnum.LOGIC, "items", "数据类型", FieldTypeEnum.SELECT,true, Arrays.asList(DataTypeEnum.values()));
        builder.addPropertyGroup(PropertyTypeEnum.LOGIC, "items", "新值", FieldTypeEnum.TEXT,  false, null);


        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "items", "$r.data001.name STRING zhangsan", "将 zhangsan 重新赋值给组件 data001 的 name 字段");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "items", "$r.data001.age INT 1", "将 1 重新赋值给组件 data001 的 age 字段");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "items", "$r.data001.desc VARIABLE $r.data002.desc", "将组件 data002 的 desc 字段赋值给组件 data001 的 desc 字段");


        builder.setResult("是否全部赋值成功 true | false", false);
        return builder.get();
    }
}
