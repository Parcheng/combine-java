package com.parch.combine.component.data.verify;

import com.parch.combine.core.settings.handler.CompareGroupSettingHandler;
import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

/**
 * 设置处理器
 */
public class DataVerifySettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("verify", "数据校验组件", false, DataVerifyComponent.class);
        // builder.addDesc("数据校验");

        builder.addProperty(PropertyTypeEnum.LOGIC, "mode", FieldTypeEnum.SELECT, "验证模式，默认为 FIRST",  false, false, "FIRST");
        builder.addPropertyOption(PropertyTypeEnum.LOGIC, "mode", "FIRST", "返回首条校验不通过信息", null);
        builder.addPropertyOption(PropertyTypeEnum.LOGIC, "mode", "ALL", "返回全部校验不通过信息", null);
        builder.addProperty(PropertyTypeEnum.LOGIC, "defaultMsg", FieldTypeEnum.TEXT, "默认错误提示信息，会拼接在 items 中配置错误提示信息之前",  false, false, "false");
        builder.addProperty(PropertyTypeEnum.LOGIC, "items", FieldTypeEnum.GROUP, "格式化配置集合",  false, true);
        CompareGroupSettingHandler.set(PropertyTypeEnum.LOGIC, builder, "items");
        builder.addProperty(PropertyTypeEnum.LOGIC, "items.msg", FieldTypeEnum.TEXT, "错误提示信息",  true, false);


        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "mode", "ALL", "返回全部的校验不通过信息");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "defaultMsg", "入参检验不通过：", "所有的错误都会在前面拼接该段文本");
        CompareGroupSettingHandler.setEg(PropertyTypeEnum.LOGIC, builder, "items");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "items.msg", "名称不正确", "条件成立时，返回“名称不正确”错误信息");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "items.msg", "#{$r.data001.error}", "条件成立时，返回 data001 组件返回结果的 error 字段的值");


        builder.setResult("校验结果的错误提示信息（或错误提示信息集合）", false);
        return builder.get();
    }
}
