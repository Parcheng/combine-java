package com.parch.combine.component.file.output.disk;

import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

/**
 * 设置处理器
 */
public class FileOutputDiskSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("output.disk", "文件数据写入磁盘组件", false, FileExportDiskComponent.class);
        // builder.addDesc("文件数据写入磁盘");


        builder.addProperty(PropertyTypeEnum.LOGIC, "source", FieldTypeEnum.TEXT, "文件的数据来源",  true, false);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "source", "必须是文件的字节数据");
        builder.addProperty(PropertyTypeEnum.LOGIC, "targetPath", FieldTypeEnum.TEXT, "文件写入的路径",  false, false);


        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "source", "$r.data001", "将 data001 组件的结果数据（文件字节数据）保存磁盘");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "targetPath", "temp/test.txt", "写入到项目 target/class/temp/test.txt 文件中");


        builder.setResult("文件写入磁盘是否成功", false);

        return builder.get();
    }
}
