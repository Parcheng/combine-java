package com.parch.combine.core.ui.base.element.dataload;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.ui.settings.PageSettingCanstant;

/**
 * 数据加载配置
 */
@CommonObject(order = 2, key = PageSettingCanstant.DATA_LOAD_KEY, name = "加载文件数据源配置", desc = "当 TYPE = FILE 时的参数列表")
public class FileDataLoadEntity extends DataLoadEntity {

    @Field(key = "path", name = "文件路径", type = FieldTypeEnum.TEXT, isRequired = true)
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
