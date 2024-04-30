package com.parch.combine.components.file.output.download;

import com.parch.combine.components.file.output.FileOutputLogicConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldEg;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 文件保存逻辑配置类
 */
public class FileOutputDownloadLogicConfig extends FileOutputLogicConfig {

    /**
     * 文件名称
     */
    @ComponentField(key = "name", name = "文件名称", type = FieldTypeEnum.TEXT, isRequired = true)
    @ComponentFieldEg(eg = "#{name}", desc = "从入参的 name 字段作为文件名称")
    private String name;

    /**
     * 文件后缀
     */
    @ComponentField(key = "postfix", name = "文件后缀", type = FieldTypeEnum.TEXT)
    @ComponentFieldEg(eg = "txt", desc = "文件为TXT文件")
    private String postfix;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostfix() {
        return postfix;
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }
}
