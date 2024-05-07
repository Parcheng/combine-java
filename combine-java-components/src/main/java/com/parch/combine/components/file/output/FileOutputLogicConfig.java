package com.parch.combine.components.file.output;

import com.parch.combine.core.component.base.LogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.nio.charset.Charset;

/**
 * 文件到处逻辑配置基类
 */
public class FileOutputLogicConfig extends LogicConfig {

    @Field(key = "source", name = "文件的数据来源", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldDesc("必须是文件的字节数据")
    @FieldEg(eg = "$r.data001", desc = "获取 data001 组件的结果数据（文件字节数据）")
    private String source;

    @Field(key = "charset", name = "字符集编码", type = FieldTypeEnum.TEXT, defaultValue = "默认根据系统环境决定")
    @FieldDesc("注意：仅在文本数据时生效")
    private Charset charset = java.nio.charset.Charset.defaultCharset();

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        try {
            this.charset = java.nio.charset.Charset.forName(charset);
        } catch (Exception e) {
            this.charset = null;
        }
    }
}
