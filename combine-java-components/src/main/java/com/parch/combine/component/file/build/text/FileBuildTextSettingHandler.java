package com.parch.combine.component.file.build.text;

import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

/**
 * 设置处理器
 */
public class FileBuildTextSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("build.text", "构建文本文件数据组件", false, FileBuildTextComponent.class);
        // builder.addDesc("创建表格文件数据");


        builder.addProperty(PropertyTypeEnum.LOGIC, "source", FieldTypeEnum.TEXT, "表格的数据来源",  true, false);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "source", "必须是对象或者对象集合的格式");


        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "source", "$r.data001", "要将ID为 data001 组件的结果数据，转换成文件的字节数据");


        builder.setResult("文件的字节数据，可以下载/保存成 xlsx 文件（其他格式不行）", false);

        return builder.get();
    }
}
