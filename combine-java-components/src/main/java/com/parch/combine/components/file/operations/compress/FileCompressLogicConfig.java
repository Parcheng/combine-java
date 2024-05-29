package com.parch.combine.components.file.operations.compress;

import com.parch.combine.core.component.base.LogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 文件压缩逻辑配置类
 */
public class FileCompressLogicConfig extends LogicConfig {

    @Field(key = "source", name = "数据来源路径", type = {FieldTypeEnum.TEXT, FieldTypeEnum.EXPRESSION}, isRequired = true)
    @FieldDesc("该字段如果为路径，则表示要对该路径压缩；如果为压缩文件地址，则表示要对改压缩包解压")
    private String source;

    @Field(key = "target", name = "写入路径", type = {FieldTypeEnum.TEXT, FieldTypeEnum.EXPRESSION}, isRequired = true)
    @FieldDesc("该字段如果为路径，则表示要将压缩文件解压到该路径；如果为压缩文件地址，则表示要压缩为该文件")
    private String target;

    @Override
    public void init() {}

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
