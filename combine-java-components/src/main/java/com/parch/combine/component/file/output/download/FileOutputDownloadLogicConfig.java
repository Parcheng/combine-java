package com.parch.combine.component.file.output.download;

import com.parch.combine.component.file.output.FileOutputLogicConfig;

/**
 * 文件保存逻辑配置类
 */
public class FileOutputDownloadLogicConfig extends FileOutputLogicConfig {

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件后缀
     */
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
