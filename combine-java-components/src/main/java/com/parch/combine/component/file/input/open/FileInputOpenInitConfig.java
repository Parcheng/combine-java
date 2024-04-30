package com.parch.combine.component.file.input.open;

import com.parch.combine.core.base.InitConfig;

/**
 * 文件初始化配置类
 */
public class FileInputOpenInitConfig extends InitConfig {
    /**
     * 下载目录
     */
    private String dir;

    /**
     * 文件大小限制（MB）
     */
    private Integer max;

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }
}
