package com.parch.combine.component.file.input.upload;

import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;

/**
 * 设置处理器
 */
public class FileInputUploadSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("input.upload", "读取上传文件数据组件", false, FileInputUploadComponent.class);
        builder.addDesc("读取上传文件数据数据");

        builder.setResult("上传的文件字节信息", false);
        return builder.get();
    }
}
