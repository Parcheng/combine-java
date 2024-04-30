package com.parch.combine.component.file.read;

import com.parch.combine.core.base.LogicConfig;

/**
 * 文件解析逻辑配置类
 */
public abstract class FileReadLogicConfig extends LogicConfig {

    /**
     * 数据来源（为空表示从接口上传的文件）
     */
    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
