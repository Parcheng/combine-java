package com.parch.combine.components.file.input.open;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.InitConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 文件初始化配置类
 */
public class FileInputOpenInitConfig extends InitConfig {

    @Field(key = "useSystemDir", name = "是否使用系统资源路径", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    @FieldDesc("使用项目中的路径路径作为基础路径")
    private Boolean useSystemDir;

    @Field(key = "dir", name = "根路径", type = FieldTypeEnum.TEXT)
    private String dir;

    @Field(key = "max", name = "文件大小限制（MB）", type = FieldTypeEnum.NUMBER)
    private Integer max;

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

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Boolean getUseSystemDir() {
        return useSystemDir;
    }

    public void setUseSystemDir(Boolean useSystemDir) {
        this.useSystemDir = useSystemDir;
    }
}
