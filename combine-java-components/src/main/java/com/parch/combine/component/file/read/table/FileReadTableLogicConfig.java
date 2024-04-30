package com.parch.combine.component.file.read.table;

import com.parch.combine.component.file.read.FileDataReadLogicConfig;

/**
 * 文件解析逻辑配置类
 */
public class FileReadTableLogicConfig extends FileDataReadLogicConfig {

    /**
     * 分隔符（文件为表格时有效）
     */
    private String separator;

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }
}
