package com.parch.combine.tool.base.crontab;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.IInitConfig;

public interface ToolCrontabInitConfig extends IInitConfig {

    @Field(key = "poolSize", name = "定时任务线程池大小", type = FieldTypeEnum.NUMBER, defaultValue = "10")
    Integer poolSize();
}
