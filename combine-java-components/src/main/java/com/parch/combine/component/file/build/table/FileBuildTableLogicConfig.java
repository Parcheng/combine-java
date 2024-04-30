package com.parch.combine.component.file.build.table;

import com.parch.combine.component.file.build.FileBuildLogicConfig;

import java.util.List;

/**
 * 文件表格写逻辑配置类
 */
public class FileBuildTableLogicConfig extends FileBuildLogicConfig {

    /**
     * 头
     */
    private List<String> header;

    /**
     * 列字段集合（数据为对象时有效）
     */
    private List<String> filedNames;

    public List<String> getHeader() {
        return header;
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }

    public List<String> getFiledNames() {
        return filedNames;
    }

    public void setFiledNames(List<String> filedNames) {
        this.filedNames = filedNames;
    }
}
