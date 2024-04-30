package com.parch.combine.component.file.output.disk;

import com.parch.combine.component.file.output.FileOutputLogicConfig;

/**
 * 文件保存逻辑配置类
 */
public class FileOutputDiskLogicConfig extends FileOutputLogicConfig {

    /**
     * 目标路径
     */
    private String targetPath;

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }
}
