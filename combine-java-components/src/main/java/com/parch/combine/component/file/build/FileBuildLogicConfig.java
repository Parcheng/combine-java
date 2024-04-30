package com.parch.combine.component.file.build;

import com.parch.combine.core.base.LogicConfig;

/**
 * 文件解析逻辑配置类
 */
public abstract class FileBuildLogicConfig extends LogicConfig {
    /**
     * 文件名
     */
    private String fileName;

    /**
     * 数据来源字段名
     */
    private String source;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
