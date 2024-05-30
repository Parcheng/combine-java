package com.parch.combine.components.file.output.download;

import com.parch.combine.components.file.output.FileOutputLogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 文件保存逻辑配置类
 */
public class FileOutputDownloadLogicConfig extends FileOutputLogicConfig {

    /**
     * 文件名称
     */
    @Field(key = "name", name = "文件名称", type = {FieldTypeEnum.TEXT, FieldTypeEnum.EXPRESSION}, isRequired = true)
    @FieldEg(eg = "#{name}", desc = "从入参的 name 字段作为文件名称")
    private String name;

    /**
     * 文件后缀
     */
    @Field(key = "postfix", name = "文件后缀", type = FieldTypeEnum.TEXT)
    @FieldEg(eg = "txt", desc = "文件为TXT文件")
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
