package com.parch.combine.components.file.operations.compress;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldDesc;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 文件压缩逻辑配置类
 */
public class FileCompressLogicConfig extends LogicConfig {

    @ComponentField(key = "source", name = "数据来源路径", type = FieldTypeEnum.TEXT, isRequired = true)
    @ComponentFieldDesc("该字段如果为路径，则表示要对该路径压缩；如果为压缩文件地址，则表示要对改压缩包解压")
    private String source;

    @ComponentField(key = "target", name = "写入路径", type = FieldTypeEnum.TEXT, isRequired = true)
    @ComponentFieldDesc("该字段如果为路径，则表示要将压缩文件解压到该路径；如果为压缩文件地址，则表示要压缩为该文件")
    private String target;

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
