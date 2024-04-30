package com.parch.combine.component.file.output;

import com.parch.combine.core.base.LogicConfig;

import java.nio.charset.Charset;

/**
 * 文件到处逻辑配置基类
 */
public class FileOutputLogicConfig extends LogicConfig {

    /**
     * 数据来源字段
     */
    private String source;

    /**
     * 字符编码
     */
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
