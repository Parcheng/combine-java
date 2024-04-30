package com.parch.combine.component.file.output.disk;

import com.parch.combine.core.base.InitConfig;

/**
 * 文件保存初始化配置类
 */
public class FileOutputDiskInitConfig extends InitConfig {

    /**
     * 下载目录
     */
    private String dir;

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}
