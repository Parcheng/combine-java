package com.parch.combine.ui.base.dataload;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.core.component.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 数据加载配置
 */
@ComponentCommonObject(order = 2, key = WebSettingCanstant.DATA_LOAD_KEY, name = "加载文件数据源配置", desc = "当 TYPE = FILE 时的参数列表")
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
