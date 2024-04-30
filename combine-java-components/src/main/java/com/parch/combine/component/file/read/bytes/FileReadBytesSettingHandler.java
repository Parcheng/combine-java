package com.parch.combine.component.file.read.bytes;

import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

/**
 * 设置处理器
 */
public class FileReadBytesSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("read.bytes", "读取字节文件组件", false, FileReadBytesComponent.class);
        builder.addDesc("文件数据读取为字节");

        builder.addProperty(PropertyTypeEnum.LOGIC, "source", FieldTypeEnum.TEXT, "数据来源",  true, false);


        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "source", "$r.data001", "读取 ID 为 data001 组件的返回结果作为文件数据，解析成数据");


        builder.setResult("文件的字节数据", false);

        return builder.get();
    }
}
