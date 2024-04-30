package com.parch.combine.components.file.operations;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.base.InitConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldDesc;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 初始化配置类
 */
public class FileOperationsInitConfig extends InitConfig {

    @ComponentField(key = "useSystemDir", name = "是否使用系统资源路径", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    @ComponentFieldDesc("使用项目中的路径路径作为基础路径")
    private Boolean useSystemDir;

    @ComponentField(key = "dir", name = "根路径", type = FieldTypeEnum.TEXT)
    private String dir;

    @Override
    public void init() {
        if (CheckEmptyUtil.isEmpty(this.dir)) {
            this.dir = CheckEmptyUtil.EMPTY;
        }
        if (this.useSystemDir == null) {
            this.useSystemDir = true;
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
