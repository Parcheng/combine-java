package com.parch.combine.component.file.output.download;

import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

/**
 * 设置处理器
 */
public class FileOutputDownloadSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("output.download", "文件数据下载组件", false, FileExportDownloadComponent.class);
        // builder.addDesc("文件数据下载");

        builder.addProperty(PropertyTypeEnum.LOGIC, "source", FieldTypeEnum.TEXT, "文件的数据来源",  true, false);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "source", "必须是文件的字节数据");
        builder.addProperty(PropertyTypeEnum.LOGIC, "charset", FieldTypeEnum.TEXT, "文本的字符集编码",  false, false);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "charset", "默认根据系统环境决定，仅在文本数据时生效");
        builder.addProperty(PropertyTypeEnum.LOGIC, "name", FieldTypeEnum.TEXT, "文件名称",  true, false);
        builder.addProperty(PropertyTypeEnum.LOGIC, "postfix", FieldTypeEnum.TEXT, "文件后缀",  true, false);


        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "source", "$r.data001", "将 data001 组件的结果数据（文件字节数据）保存磁盘");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "name", "#{name}", "从入参的 name 字段作为文件名称");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "postfix", "txt", "文件为TXT文件");


        builder.setResult("文件写入磁盘是否成功", true);

        return builder.get();
    }
}
