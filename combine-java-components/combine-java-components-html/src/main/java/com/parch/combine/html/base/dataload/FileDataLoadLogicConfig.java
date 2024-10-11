package com.parch.combine.html.base.dataload;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.base.dataload.core.DataLoadConfig;

public interface FileDataLoadLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "数据加载配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(Config.class)
    Config config();

    interface Config extends DataLoadConfig {

        @Field(key = "path", name = "文件路径", type = FieldTypeEnum.TEXT, isRequired = true)
        String path();
    }
}
