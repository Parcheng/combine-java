package com.parch.combine.component.file.input.open;

import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

/**
 * 设置处理器
 */
public class FileInputOpenSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("input.open", "读取磁盘文件数据组件", false, FileInputOpenComponent.class);
        builder.addDesc("读取磁盘文件数据数据");

        builder.addProperty(PropertyTypeEnum.LOGIC, "path", FieldTypeEnum.TEXT, "文件路径",  true, false);


        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "path", "my/test.txt", "打开项目资源目录下 target/class/files/my/test.txt 文件");


        builder.setResult("打开的文件字节信息", false);

        return builder.get();
    }
}
