package com.parch.combine.components.file.output.disk;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.base.InitConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldDesc;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 文件保存初始化配置类
 */
public class FileOutputDiskInitConfig extends InitConfig {

    @ComponentField(key = "useSystemDir", name = "是否使用系统资源路径", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    @ComponentFieldDesc("使用项目中的路径路径作为基础路径")
    private Boolean useSystemDir;

    @ComponentField(key = "dir", name = "根目录", type = FieldTypeEnum.TEXT)
    private String dir;

    @Override
    public void init() {
        if (CheckEmptyUtil.isEmpty(getDir())) {
            setDir(CheckEmptyUtil.EMPTY);
        }
        if (getUseSystemDir() == null) {
            setUseSystemDir(true);
        }
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public Boolean getUseSystemDir() {
        return useSystemDir;
    }

    public void setUseSystemDir(Boolean useSystemDir) {
        this.useSystemDir = useSystemDir;
    }
}
