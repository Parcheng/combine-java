package com.parch.combine.ui.core.base.dataload;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.settings.annotations.CommonObject;

import java.util.List;

/**
 * 数据加载配置
 */
@CommonObject(order = 2, name = "加载文件数据源配置", desc = "当 TYPE = FILE 时的参数列表")
public class FileDataLoadConfig extends DataLoadConfig {

    @Field(key = "path", name = "文件路径", type = FieldTypeEnum.TEXT, isRequired = true)
    private String path;

    @Override
    public void init() {}

    @Override
    public List<String> check() {
        return null;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
